public class MMOCharacter {
    private final String name;
    private final String token;

    public MMOCharacter(String token, String name) {
        this.token = token;
        this.name = name;
    }

    public int move(int x, int y) {
        String urlToRead = "https://api.artifactsmmo.com/my/%s/action/move".formatted(name);

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

    public int fight() {
        String urlToRead = "https://api.artifactsmmo.com/my/%s/action/fight".formatted(name);

        Pair<Integer, String> res = MyHTTPSRequest.postRequest(urlToRead, token, null);

        System.out.println(res.last);

        return res.first;
    }

    public int gathering() {
        String urlToRead = "https://api.artifactsmmo.com/my/%s/action/gathering".formatted(name);
        
        Pair<Integer, String> res = MyHTTPSRequest.postRequest(urlToRead, token, null);

        System.out.println(res.last);

        return res.first;
    }

    public int unequip(String slot) {
        String urlToRead = "https://api.artifactsmmo.com/my/%s/action/unequip".formatted(name);

        String data = """
                    {
                        "slot":"%s"
                    }
                    """.formatted(slot);

        Pair<Integer, String> res = MyHTTPSRequest.postRequest(urlToRead, token, data);

        System.out.println(res.last);

        return res.first;
    }

    public int crafting(String code) {
        String urlToRead = "https://api.artifactsmmo.com/my/%s/action/crafting".formatted(name);

        String data = """
                    {
                        "code":"%s"
                    }
                    """.formatted(code);

        Pair<Integer, String> res = MyHTTPSRequest.postRequest(urlToRead, token, data);

        System.out.println(res.last);

        return res.first;
    }

    public int equip(String code, String slot) {
        String urlToRead = "https://api.artifactsmmo.com/my/%s/action/equip".formatted(name);

        String data = """
                    {
                        "code":"%s",
                        "slot":"%s"
                    }
                    """.formatted(code, slot);

        Pair<Integer, String> res = MyHTTPSRequest.postRequest(urlToRead, token, data);

        System.out.println(res.last);

        return res.first;
    }
}
