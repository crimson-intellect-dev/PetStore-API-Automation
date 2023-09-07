package api.endpoints;

// In this class we are only maintaining the urls / routes - no implementations )no methods)
public class Routes {
    public static String base_url = "https://petstore.swagger.io/v2";

    //  create end points for user module
    public static String post_url = base_url + "/user";
    //    {username is a path parameter}
    public static String get_url = base_url + "/user/{username}";
    public static String update_url = base_url + "/user/{username}";
    public static String delete_url = base_url + "/user/{username}";

//    create end points for store module
    //    create store module urls

//    pet module
    //    create pet urls

}
