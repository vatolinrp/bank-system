<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
	<h1>Hello, please enter login and password</h1>

	<form action="/bank-1/login" method="post">
        <table>
          <tr>
            <td>Enter login:</td>
            <td><input type="text" name="login"/></td>
          </tr>
          <tr>
            <td>Enter password:</td>
            <td><input type="password" name="password"/></td>
          </tr>
        </table>
        <input type="submit" value="login" />
    </form>

</body>
</html>