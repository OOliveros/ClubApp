<%@page import="app.model.Campo"%>
<%@page import="java.util.List"%>
<%@page import="app.model.Local"%>
<%@page import="app.zelper.Constants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Local> locales = (List<Local>) request.getAttribute("locales");
    Campo campo = (Campo) request.getAttribute("campo");

%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title> Campos - Nuevo Campo </title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <%@include file="/public/header.jsp" %>
    </head>

    <body>

        <%@include file="/public/menuGeneral.jsp" %>

        <div class="container-fluid">
            <div class="row-fluid">

                <%@include file="/public/menuAdm.jsp" %>

                <div class="span9">
                    <div class="row">
                        <h1> Nuevo Campo Deportivo </h1>
                    </div>

                    <form action="<%=contextPath%>/adm/campo" method="post">

                        <input hidden="id" value="${campo.id}" name="id">

                        <div class="control-group">
                            <label class="control-label">Local</label>
                            <div class="controls">
                                <select type="text" name="local">
                                    <% for (Local local : locales) {%>
                                        
                                        <% if (campo.getLocal()!=null && local.getId() == campo.getLocal().getId()) {%>
                                            <option value="<%=local.getId()%>" selected><%=local.getDescripcion()%></option>
                                        <% } else {%>
                                             <option value="<%=local.getId()%>"><%=local.getDescripcion()%></option>
                                        <% }%>
                                    <% }%>

                                </select>
                            </div>
                        </div>


                        <div class="control-group">
                            <label class="control-label">Descripción</label>
                            <div class="controls">
                                <input type="text" name="descripcion" value="${campo.descripcion}">
                            </div>
                        </div>


                        <div class="control-group">
                            <label class="control-label">Costo/Hora</label>
                            <div class="controls">
                                <input type="text" name="costo" value="${campo.costoHora}">
                            </div>
                        </div>


                        <div class="control-group">
                            <div class="controls">
                                <a class="btn" href="<%=contextPath%>/adm/campo">Cancelar</a>
                                <button type="submit" class="btn btn-primary">Guardar</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>


            <hr>
        </div>

        <%@include file="/public/footer.jsp" %>
    </body>
</html>




