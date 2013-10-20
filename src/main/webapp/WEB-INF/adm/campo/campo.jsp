<%@page import="app.model.Campo"%>
<%@page import="java.util.List"%>
<%@page import="app.zelper.Constants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Campo> campos = (List<Campo>) request.getAttribute("campos");
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title> Campos Deportivos </title>
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
                        <a class=" btn btn-primary pull-right" href="<%=contextPath%>/adm/campo?action=<%=Constants.ACTION_CREATE%>"> Nuevo </a>
                        <h1> Campos Deportivos </h1>
                    </div>
                    
                    <% if (!campos.isEmpty()) {%>
                    <table class="table table-striped table-hover"> 
                        <thead>
                        <th> Local </th>
                        <th> Descripción </th>
                        <th> Costo/Hora </th>
                        <th></th>
                        </thead>
                        <tbody>
                            <% for (Campo campo : campos) {%>
                            <tr>
                                <td> <%=campo.getLocal().getDescripcion()%> </td>
                                <td> <%=campo.getDescripcion()%> </td>
                                <td> <%=campo.getCostoHora()%> </td>
                                <td> 
                                    <div class="btn-group">
                                        <a class="dropdown-toggle" data-toggle="dropdown" role="menu"  href="#">
                                          <i class="icon-cog"></i>
                                        </a>
                                        <ul class="dropdown-menu pull-right">
                                            <li>
                                                <a href="<%=contextPath%>/adm/campo?action=<%=Constants.ACTION_UPDATE%>&id=<%=campo.getId()%>">
                                                Editar<a>
                                            </li>
                                            <li>
                                                <a href="<%=contextPath%>/adm/campo?action=<%=Constants.ACTION_DELETE%>&id=<%=campo.getId()%>">
                                                    Eliminar<a>
                                            </li>
                                        </ul>
                                      </div>
                                </td>
                            </tr>
                            <% }%>
                        </tbody>
                    </table>
                        
                    <% }%>
                </div>
            </div>


            <hr>
        </div>

        <%@include file="/public/footer.jsp" %>
    </body>
</html>




