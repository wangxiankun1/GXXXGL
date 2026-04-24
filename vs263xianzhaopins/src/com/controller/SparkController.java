package com.controller;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.*;

@RestController
@RequestMapping("/api/spark")
public class SparkController {

    // 改成你自己的 Bearer Token
    private static final String BEARER = "Bearer iVvovhpRVyesvZTXuMqW:SIeUKdGgDXAlmupRuCHo";

    // 这里必须改成 v2
    private static final String API_URL = "https://spark-api-open.xf-yun.com/v1/chat/completions";

    // 这里必须改成控制台生成代码里的 model
    //private static final String MODEL = "spark-x";
    private static final String MODEL = "lite";
    private static final int CONNECT_TIMEOUT_MS = 15000;
    private static final int READ_TIMEOUT_MS = 60000;

    // 简单上下文历史
    private final List<Message> history = Collections.synchronizedList(new ArrayList<Message>());

    @RequestMapping(value = "/ask", method = RequestMethod.POST)
    public Map<String, Object> ask(@RequestBody Map<String, Object> param, HttpServletResponse response) {
        Map<String, Object> result = new HashMap<String, Object>();
        long start = System.currentTimeMillis();

        try {
            String question = param.containsKey("prompt") ? String.valueOf(param.get("prompt")) : "";
            if (question == null || question.trim().isEmpty()) {
                result.put("code", 400);
                result.put("msg", "问题不能为空");
                return result;
            }

            System.out.println("[Spark] /ask prompt: " + question);

            // 保存用户消息
            history.add(new Message("user", question));

            // 只取最近 10 条，避免上下文过长
            List<Message> recentHistory = getRecentHistory(history, 10);

            Map<String, Object> payload = new LinkedHashMap<String, Object>();
            payload.put("max_tokens", 4096);
            payload.put("top_k", 6);
            payload.put("temperature", 1.2);
            payload.put("messages", recentHistory);
            payload.put("model", MODEL);

            // 如果你不需要联网搜索，就保持 false
            List<Map<String, Object>> tools = new ArrayList<Map<String, Object>>();
            Map<String, Object> tool = new HashMap<String, Object>();
            Map<String, Object> webSearch = new HashMap<String, Object>();
            webSearch.put("search_mode", "normal");
            webSearch.put("enable", false);
            tool.put("web_search", webSearch);
            tool.put("type", "web_search");
            tools.add(tool);

            payload.put("tools", tools);
            payload.put("stream", false); // 先用非流式，前端更好处理

            HttpResp api = postJson(API_URL, payload, BEARER);

            System.out.println("[Spark] upstream status=" + api.status
                    + " body=" + truncate(api.body, 2000));

            if (api.status >= 400) {
                response.setStatus(502);
                result.put("code", api.status);
                result.put("msg", "上游错误（" + api.status + "）：" + safeMsg(api.body));
                result.put("upstreamBody", api.body);
                return result;
            }

            Gson gson = new Gson();
            SparkResponse aiResp = gson.fromJson(api.body, SparkResponse.class);

            String reply = "";
            if (aiResp != null
                    && aiResp.choices != null
                    && !aiResp.choices.isEmpty()
                    && aiResp.choices.get(0) != null
                    && aiResp.choices.get(0).message != null
                    && aiResp.choices.get(0).message.content != null) {
                reply = aiResp.choices.get(0).message.content;
            } else {
                reply = api.body;
            }

            history.add(new Message("assistant", reply));

            result.put("code", 200);
            result.put("msg", "ok");
            result.put("answer", reply);
            result.put("data", aiResp);
            return result;

        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            response.setStatus(504);
            result.put("code", 504);
            result.put("msg", "上游读取超时：" + e.getMessage());
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(500);
            result.put("code", 500);
            result.put("msg", "服务内部异常：" + e.getMessage());
            return result;
        } finally {
            System.out.println("[Spark] /ask cost=" + (System.currentTimeMillis() - start) + "ms");
        }
    }

    public static HttpResp postJson(String url, Map<String, Object> data, String bearer) throws IOException {
        HttpURLConnection con = null;
        OutputStream os = null;
        InputStream is = null;
        BufferedReader in = null;

        try {
            URL obj = new URL(url);
            con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Authorization", bearer);
            con.setConnectTimeout(CONNECT_TIMEOUT_MS);
            con.setReadTimeout(READ_TIMEOUT_MS);
            con.setDoOutput(true);

            String json = new Gson().toJson(data);
            System.out.println("[Spark] upstream req body=" + truncate(json, 2000));

            os = con.getOutputStream();
            os.write(json.getBytes("UTF-8"));
            os.flush();

            int status = con.getResponseCode();

            if (status >= 400) {
                is = con.getErrorStream();
            } else {
                is = con.getInputStream();
            }

            String body = "";
            if (is != null) {
                in = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    sb.append(line);
                }
                body = sb.toString();
            }

            HttpResp resp = new HttpResp();
            resp.status = status;
            resp.body = body;
            return resp;

        } finally {
            try { if (in != null) in.close(); } catch (Exception ignore) {}
            try { if (is != null) is.close(); } catch (Exception ignore) {}
            try { if (os != null) os.close(); } catch (Exception ignore) {}
            if (con != null) con.disconnect();
        }
    }

    private List<Message> getRecentHistory(List<Message> list, int maxSize) {
        if (list == null || list.size() <= maxSize) {
            return new ArrayList<Message>(list);
        }
        return new ArrayList<Message>(list.subList(list.size() - maxSize, list.size()));
    }

    private static String truncate(String s, int limit) {
        if (s == null) return null;
        return s.length() <= limit ? s : s.substring(0, limit) + "...";
    }

    private static String safeMsg(String raw) {
        if (raw == null) return null;
        return truncate(raw.replaceAll("\\p{Cntrl}", ""), 2000);
    }

    static class HttpResp {
        int status;
        String body;
    }

    static class Message {
        public String role;
        public String content;

        public Message() {}

        public Message(String role, String content) {
            this.role = role;
            this.content = content;
        }
    }

    static class SparkResponse {
        public List<Choice> choices;
    }

    static class Choice {
        public Message message;
    }
}