public class MMOCharacter {
    private final String name;
    private final String token;

    public MMOCharacter(String token, String name) {
        this.token = token;
        this.name = name;
    }

    public int move(int x, int y) {
        String urlToRead = "https://api.artifactsmmo.com/my/" + this.name + "/action/move";

        String data = """
                    {
                        "x":%d,
                        "y":%d
                    }
                    """.formatted(x, y);

        Pair<Integer, String> res = MyHTTPSRequest.postRequest(urlToRead, token, data);

        System.out.println(res.last);

        return res.first;
    }
}
