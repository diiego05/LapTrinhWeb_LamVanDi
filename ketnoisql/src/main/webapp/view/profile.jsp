<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<div class="container-fluid px-4">
    <h1 class="mt-4">User Profile</h1>
    <ol class="breadcrumb mb-4">
        <li class="breadcrumb-item active">Update your profile information</li>
    </ol>

    <div class="card mb-4">
        <div class="card-body">
            <form action="<c:url value='/member/profile'/>" method="post" enctype="multipart/form-data">

                <div class="mb-3">
                    <label class="form-label">Current Avatar:</label><br>
                    <c:url value="/image?fname=${sessionScope.acc.avatarUrl}" var="imgUrl"/>
                    <img src="${imgUrl}" alt="Avatar" width="150">
                </div>

                <div class="mb-3">
                    <label for="fullname" class="form-label">Full Name</label>
                    <input type="text" class="form-control" id="fullname" name="fullname" value="${sessionScope.acc.fullname}">
                </div>

                <div class="mb-3">
                    <label for="phone" class="form-label">Phone Number</label>
                    <input type="text" class="form-control" id="phone" name="phone" value="${sessionScope.acc.phone}">
                </div>

                <div class="mb-3">
                    <label for="avatar" class="form-label">Change Avatar</label>
                    <input class="form-control" type="file" id="avatar" name="avatar">
                </div>

                <button type="submit" class="btn btn-primary">Update Profile</button>
            </form>
        </div>
    </div>
</div>