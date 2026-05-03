<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Authors — LibraryMS</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<nav>
    <a class="nav-brand" href="${pageContext.request.contextPath}/">📚 LibraryMS</a>
    <ul class="nav-links">
        <li><a href="${pageContext.request.contextPath}/">Home</a></li>
        <li><a href="${pageContext.request.contextPath}/authors" class="active">Authors</a></li>
        <li><a href="${pageContext.request.contextPath}/books">Books</a></li>
    </ul>
</nav>

<main>
    <c:if test="${not empty success}">
        <div class="alert alert-success">✓ ${success}</div>
    </c:if>
    <c:if test="${not empty error}">
        <div class="alert alert-error">✕ ${error}</div>
    </c:if>

    <div class="page-header">
        <h1 class="page-title">Authors</h1>
        <a href="${pageContext.request.contextPath}/authors/add" class="btn btn-primary">+ Add Author</a>
    </div>

    <div class="card">
        <div class="table-wrapper">
            <table>
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Name</th>
                        <th>Nationality</th>
                        <th>Bio</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="author" items="${authors}">
                        <tr>
                            <td>${author.id}</td>
                            <td>${author.name}</td>
                            <td>${author.nationality}</td>
                            <td class="bio-cell" title="${author.bio}">${author.bio}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/authors/edit/${author.id}" class="btn btn-secondary btn-sm">Edit</a>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty authors}">
                        <tr>
                            <td colspan="5" style="text-align:center; color: #64748b; padding: 2rem;">No authors found.</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </div>
</main>

</body>
</html>
