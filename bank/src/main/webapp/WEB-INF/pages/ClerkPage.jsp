<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <body>
        <h1>Hello, ${name}!</h1>
        <h3>Here is a list of requests for you:</h3>
        ${requests}

        <h2> <a href="/bank-1/goback">Go back to login page</a></h2>

        <form action="/bank-1/save-request" method="post">
            <table>
              <tr>
                <td>Create new request:</td>
                <td><textarea type="text" name="request-content">${contentForEdit}</textarea></td>
                ${contentForView}
              </tr>
            </table>
            <input type="hidden" name="request-id" value="${requestId}">
            <input type="hidden" name="appr-by-ref" value="${apprByRef}">
            <input type="submit" value="save" />
        </form>
         <form action="/bank-1/request-agree-with-cond/${requestId}" method="post">
              <input type="submit" value="ask for creation" />
         </form>
    </body>
</html>
