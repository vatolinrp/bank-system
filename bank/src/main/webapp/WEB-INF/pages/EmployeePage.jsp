<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <body>
        <h1>Hello, ${name}!</h1>
        <h3>Here is a list of requests for you:</h3>
        ${requests}

        <h2> <a href="/bank-1/goback">Go back to login page</a></h2>

        <form action="/bank-1/decision-from-emp" method="post">
            <table>
              <tr>
                <td>Create new request:</td>
                <td><textarea type="text" readonly="readonly" name="request-content">${contentForEdit}</textarea></td>
              </tr>
            </table>
            <input type="hidden" name="request-id" value="${requestId}">
            <select name="decision">
              <option value="true">Approve</option>
              <option value="false">Disapprove</option>
            </select>
            <input type="submit" value="save decision" />
        </form>
        <form action="/bank-1/request-account-state/${requestId}" method="post">
            <input type="submit" value="get account state" />
        </form>
        <form action="/bank-1/request-create/${requestId}" method="post">
            <input type="submit" value="create" />
        </form>

    </body>
</html>