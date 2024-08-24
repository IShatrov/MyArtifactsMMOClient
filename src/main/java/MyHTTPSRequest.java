import javax.net.ssl.HttpsURLConnection;
import java.io.DataOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class MyHTTPSRequest {
    private static final  int SUCCESS_CODE = 200;

    public static Pair<Integer, String> postRequest(String urlToRead, String token, String data) {
        try {
            HttpsURLConnection connection = getHttpsURLConnection(urlToRead, token);

            if (data != null) {
                writeData(connection, data);
            }

            connection.setConnectTimeout(1000);
            connection.setReadTimeout(1000);

            int status = connection.getResponseCode();

            if (status != SUCCESS_CODE) {
                System.out.println("Error " + status + " " + HTTPSErrorCode.getErrorText(status));
                connection.disconnect();
                return new Pair<>(status, null);
            }

            String res = readData(connection);

            connection.disconnect();

            return new Pair<>(status, res);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }

        return new Pair<>(-1, null);
    }

    private static HttpsURLConnection getHttpsURLConnection(String urlToRead, String token) throws Exception {
        try {
            URL url = new URL(urlToRead);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + token);

            return connection;
        } catch (Exception ex) {
            throw new Exception("Failed to establish HTTPS URL connection " + urlToRead);
        }
    }

    private static void writeData(HttpsURLConnection connection, String data) throws Exception {
        try {
            connection.setDoOutput(true);
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.writeBytes(data);
            out.flush();
            out.close();
        } catch (Exception ex) {
            throw new Exception("Failed to send data " + data);
        }
    }

    private static String readData(HttpsURLConnection connection) throws Exception {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            return content.toString();
        } catch (Exception ex) {
            throw new Exception("Failed to read data");
        }
    }
}
