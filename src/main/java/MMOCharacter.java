import javax.net.ssl.HttpsURLConnection;
import java.io.DataOutputStream;
import java.net.URL;

public class MMOCharacter {
    private final String name;
    private final String token;

    public MMOCharacter(String token, String name) {
        this.token = token;
        this.name = name;
    }

//    public String getName() {
//        return this.name;
//    }
//
//    public String getToken() {
//        return this.token;
//    }

    public int move(int x, int y) {
        String urlToRead = "https://api.artifactsmmo.com/my/" + this.name + "/action/move";

        try {
            URL url = new URL(urlToRead);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + token);

            String data = """
                    {
                        "x":%d,
                        "y":%d
                    }
                    """.formatted(x, y);

           // System.out.println(data);

            connection.setDoOutput(true);
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.writeBytes(data);
            out.flush();
            out.close();

            connection.setConnectTimeout(1000);
            connection.setReadTimeout(1000);

            int status = connection.getResponseCode();
/*        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();*/

            connection.disconnect();

            return status;
        } catch (Exception ex) {
            System.out.println("Failed to move");
        }

        return -1;
    }
}
