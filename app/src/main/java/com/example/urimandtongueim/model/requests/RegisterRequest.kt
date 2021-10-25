package requests

class RegisterRequest(var username: String, var password: String,
                      var firstName: String?, var lastName: String?,
                      var primaryLanguage: String)