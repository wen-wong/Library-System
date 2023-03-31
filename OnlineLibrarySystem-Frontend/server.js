const express = require("express");
const port = process.env.PORT || 8087;
const app = express();

app.use(express.static(__dirname + "/dist/"));
app.get(/.*/, function(req, res) {
  res.sendFile(__dirname + "/index.html");
});
app.listen(port);

console.log("Server started...");
