<%@ taglib prefix="c" uri="jakarta.tags.core" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <html>

        <head>
            <link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">

            <title>Login page</title>
        </Head>

        <body>
            <div class="container">

                <h1>Update ToDo </h1>

                <form:form method="post" modelAttribute="todo">

                    <form:input type="hidden" path="id" />

                    <form:input type="hidden" path="username" />



                    <fieldset class="mb-3">

                        <form:label path="description">Description</form:label>

                        <form:input type="text" path="description" required="required" />

                        <form:errors path="description" cssClass="text-warning" />

                    </fieldset>


                    <fieldset class="mb-3">

                        <form:label path="targetDate">Target Date</form:label>

                        <form:input type="text" path="targetDate" required="required" />

                        <form:errors path="targetDate" cssClass="text-warning" />

                    </fieldset>

                    <fieldset class="mb-3">

                        <form:label path="done">Done</form:label>

                        <form:input type="text" path="done" required="required" />

                        <form:errors path="done" cssClass="text-warning" />

                    </fieldset>

                    <button class="btn btn-success" type="submit">Update Todo</button>

                </form:form>
            </div>

            <script> src = "webjars/bootstrap/5.1.3/css/bootstrap.min.js"</script>
            <script> src = "webjars/jquery/3.6.0/jquery.min.js"</script>
        </body>


        </html>