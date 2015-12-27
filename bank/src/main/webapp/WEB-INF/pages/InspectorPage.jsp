<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <body>
        <h1>Hello, ${name}!</h1>
        <h3>Here is a list of requests for you:</h3>
        ${requests}

        <h2> <a href="/bank-1/goback">Go back to login page</a></h2>

        <form action="/bank-1/decision-from-insp" method="post">
            <table>
              <tr>
                ${contentForView}
              </tr>
               <tr>
                 <td>Conditions:</td>
                 <td><textarea type="text" name="request-conditions"></textarea></td>
               </tr>
            </table>
            <input type="hidden" name="request-id" value="${requestId}">
            <select name="decision">
              <option value="true">Approve</option>
              <option value="false">Disapprove</option>
            </select>
            <input type="submit" value="save decision" />
        </form>

    </body>
</html>