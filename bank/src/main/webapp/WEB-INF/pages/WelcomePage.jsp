<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
	<h1>Hello, please enter login and password</h1>

	<h2>${msg}</h2>


	<form action="login" method="post">
            <table>
              <tr><td>Enter name:</td>
                  <td><input type="text" name="login"   /></td></tr>
              <tr><td>Enter age:</td>
                  <td><input type="password" name="password"    /></td></tr>
            </table>
            <input type="submit" value="login" />
    </form>

</body>
</html>