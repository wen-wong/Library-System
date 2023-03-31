export default class NavBar {
    viewAccount() {
        var userType = localStorage.getItem("userType")
        if (userType == "Client") {
            window.location.href = '/#/AboutClient'
        } else if (userType == "Librarian") {
            window.location.href = '/#/AboutLib'
        } else if (userType == "HeadLibrarian") {
            window.location.href = '/#/AboutHL'
        } else if (userType == null) {
            console.log("Not logged in.")
        }
    }
}