<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="col-sm-2 sidenav">
	<p>
		<select>
			<option value="0">Select category</option>
			<jsp:include page="optionCategorySide.jsp" />
		</select>
	</p>
	<sec:authorize access="isAuthenticated() and hasRole('ROLE_ADMIN')">
		<p>
			<a href="/admin/itemForm">Create Item</a>
		</p>
		<p>
			<a href="/admin/category">Category</a>
		</p>
		<p>
			<a href="/admin/country">Country</a>
		</p>
		<p>
			<a href="/admin/brand">Brand</a>
		</p>
		<p>
			<a href="/admin/itemView">Show Items</a>
		</p>
	</sec:authorize>
	<p>
		<a href="/student">Student</a>
	</p>
	<sec:authorize access="isAuthenticated() and hasRole('ROLE_ADMIN')">
	<p>Parent null</p>
	<c:forEach items="${sideBarCategotyParentNull}" var="category">
		<p>${category.name}</p>
	</c:forEach>
	<p>ParentNotNull ChildTrue</p>
	<c:forEach items="${sideBarCategotyParentNotNullAndHaveChildsTrue}"
		var="category">
		<p>${category.name}</p>
	</c:forEach>
	<p>Child null</p>
	<c:forEach items="${sideBarCategotyChildFalse}" var="category">
		<p>${category.name}</p>
	</c:forEach>
	</sec:authorize>
</div>
