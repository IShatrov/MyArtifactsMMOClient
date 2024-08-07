import javax.net.ssl.HttpsURLConnection;
import java.io.DataOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class MyHTTPSRequest {
    public static Pair<Integer, String> postRequest(String urlToRead, String token, String data) {
        try {
            HttpsURLConnection connection = getHttpsURLConnection(urlToRead, token);

            writeData(connection, data);

            connection.setConnectTimeout(1000);
            connection.setReadTimeout(1000);

            int status = connection.getResponseCode();

            String res = readData(connection);

            connection.disconnect();

            return new Pair<>(status, res);
        } catch (Exception ex) {
            System.out.println("Failed to move");
        }

        return new Pair<>(-1, null);
    }

    private static HttpsURLConnection getHttpsURLConnection(String urlToRead, String token) throws IOException {
        URL url = new URL(urlToRead);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Bearer " + token);

        return connection;
    }

    private static void writeData(HttpsURLConnection connection, String data) throws IOException {
        connection.setDoOutput(true);
        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        out.writeBytes(data);
        out.flush();
        out.close();
    }

    private static String readData(HttpsURLConnection connection) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        return content.toString();
    }
}
