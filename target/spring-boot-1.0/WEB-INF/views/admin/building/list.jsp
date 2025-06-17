
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<html>
<head>
    <title>Danh Sách Tòa Nhà</title>
</head>
<body>
<div class="main-content" >
    <div class="main-content">
        <div class="main-content-inner">
            <div class="breadcrumbs" id="breadcrumbs">
                <script type="text/javascript">
                    try {
                        ace.settings.check("breadcrumbs", "fixed");
                    } catch (e) {}
                </script>

                <ul class="breadcrumb">
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                        <a href="#">Home</a>
                    </li>
                    <li class="active">Quản lí tòa nhà</li>
                </ul>
                <!-- /.breadcrumb -->

                <!-- /.nav-search -->
            </div>


            </div>
            <div class="row">
                <div class="col-xs-12">
                    <div class="widget-box ui-sortable-handle">
                        <div class="widget-header">
                            <h5 class="widget-title">Tìm Kiếm</h5>

                            <div class="widget-toolbar">
                                <a href="#" data-action="collapse">
                                    <i class="ace-icon fa fa-chevron-up"></i>
                                </a>
                            </div>
                        </div>

                        <div class="widget-body" style=" font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;">
                            <div class="widget-main" >
                                <form:form  id="listForm" modelAttribute="modelSearch"  method="GET" >
                                    <div class="row">
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-sm-6">
                                                    <label class="name" >Tên Tòa Nhà</label>
                                                        <form:input class="form-control" path="name"/>
                                                </div>
                                                <div class="col-sm-6">
                                                    <label class="name" >Diện Tích Sàn</label>
                                                    <form:input class="form-group" path="floorArea"/>
                                                </div>
                                            </div>
                                        </div>

                                            <div class="form-group">
                                                <div class="col-xs-12">
                                                    <div class="col-sm-2">
                                                        <label class="name" >Quận</label>
                                                        <form:select class="form-control" path="district">
                                                            <form:option value="">---Chọn Quận---</form:option>
                                                            <form:options items="${districts}"/>
                                                        </form:select>
                                                    </div>
                                                    <div class="col-sm-5">
                                                        <label class="name" >Phường</label>
                                                            <form:input class="form-control" path="ward"/>
                                                    </div>
                                                    <div class="col-sm-5">
                                                        <label class="name" >Đường</label>
                                                            <form:input class="form-control" path="street"/>
                                                    </div>
                                                </div>
                                            </div>>

                                            <div class="col-xs-12">
                                                <div class="col-sm-4">
                                                    <label class="name" >Số Tầng Hầm</label>
                                                    <form:input class="form-group" path="numberOfBasement"/>
                                                </div>
                                                <div class="col-sm-4">
                                                    <label class="name" >Hướng</label>
                                                    <form:input class="form-group" path="direction"/>
                                                </div>
                                                <div class="col-sm-4">
                                                    <label class="name" >Hạng</label>
                                                    <form:input class="form-group" path="level"/>
                                                </div>
                                            </div>

                                            <div class="col-xs-12">
                                                <div class="col-sm-3">
                                                    <label class="name">Diện Tích Từ</label>
                                                    <form:input class="form-group" path="areaFrom"/>
                                                </div>
                                                <div class="col-sm-3">
                                                    <label class="name" >Diện Tích Đến</label>
                                                    <form:input class="form-group" path="areaTo"/>
                                                </div>
                                                <div class="col-sm-3">
                                                    <label class="name" >Giá Thuê Từ</label>
                                                    <form:input class="form-group" path="rentPriceFrom"/>
                                                </div>
                                                <div class="col-sm-3">
                                                    <label class="name" >Giá Thuê Đến</label>
                                                    <form:input class="form-group" path="rentPriceTo"/>
                                                </div>
                                            </div>

                                            <div class="col-xs-12">
                                                <div class="col-sm-5">
                                                    <label class="name" >Tên Quản Lý</label>
                                                    <form:input class="form-group" path="managerName"/>
                                                </div>
                                                <div class="col-sm-5">
                                                    <label class="name" >Điện Thoại Quản Lý</label>
                                                    <form:input class="form-group" path="managerPhone"/>
                                                </div>
                                                <div class="col-sm-2">
                                                <security:authorize access="hasRole('MANAGER')">
                                                    <label class="name"
                                                    >Chọn Nhân Viên Phụ Trách</label
                                                    >
                                                    <form:select class="form-control" path="staffId">
                                                        <form:option value="">---Chọn Nhân Viện---</form:option>
                                                        <form:options items="${listStaffs}" itemValue="id" itemLabel="fullName"/>
                                                    </form:select>
                                                </security:authorize>
                                                </div>
                                            </div>

                                            <div class="col-xs-12">
                                                <div class="col-sm-6">
                                                    <form:checkboxes items="${typeCodes}" path="typeCode"/>
                                                </div>
                                            </div>

                                            <div class="col-xs-12">
                                                <div class="col-sm-6">
                                                    <button
                                                            type="button"
                                                            class="btn btn-danger"
                                                            id="btnSearchBuilding"
                                                    >
                                                        <svg
                                                                xmlns="http://www.w3.org/2000/svg"
                                                                width="16"
                                                                height="16"
                                                                fill="currentColor"
                                                                class="bi bi-search"
                                                                viewBox="0 0 16 16"
                                                        >
                                                            <path
                                                                    d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"
                                                            ></path>
                                                        </svg>
                                                        Tìm Kiếm
                                                    </button>
                                                </div>
                                            </div>
                                    </div>
                                </form:form>

                            </div>

                            <div class="pull-right">
                                <a href="/admin/building-edit">
                                    <button class="btn btn-info">
                                        <svg xmlns="http://www.w3.org/2000/svg"
                                             height="16"
                                             fill="currentColor"
                                             class="bi bi-building-add"
                                             viewBox="0 0 16 16"
                                        >
                                            <path
                                                    d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7m.5-5v1h1a.5.5 0 0 1 0 1h-1v1a.5.5 0 0 1-1 0v-1h-1a.5.5 0 0 1 0-1h1v-1a.5.5 0 0 1 1 0"
                                            />
                                            <path
                                                    d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v6.5a.5.5 0 0 1-1 0V1H3v14h3v-2.5a.5.5 0 0 1 .5-.5H8v4H3a1 1 0 0 1-1-1z"
                                            />
                                            <path
                                                    d="M4.5 2a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"
                                            />
                                        </svg>
                                    </button>
                                </a>
                                <button class="btn btn-danger" value="Xoa toa nha" id="btnDeleteBuilding">
                                    <svg
                                            xmlns="http://www.w3.org/2000/svg"
                                            width="16"
                                            height="16"
                                            fill="currentColor"
                                            class="bi bi-building-dash"
                                            viewBox="0 0 16 16"
                                    >
                                        <path
                                                d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7M11 12h3a.5.5 0 0 1 0 1h-3a.5.5 0 0 1 0-1"
                                        />
                                        <path
                                                d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v6.5a.5.5 0 0 1-1 0V1H3v14h3v-2.5a.5.5 0 0 1 .5-.5H8v4H3a1 1 0 0 1-1-1z"
                                        />
                                        <path
                                                d="M4.5 2a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"
                                        />
                                    </svg>
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <!-- bang toa nha -->
                        <div class="col-xs-12" >
                            <table
                                    id="tableList"
                                    style="margin: 3em 0 1.5em"
                                    class="table table-striped table-bordered table-hover"
                            >
                                <thead>
                                <tr>
                                    <th class="center">
                                        <label class="pos-rel">
                                            <input type="checkbox" name="checkList" value="" class="ace" />
                                            <span class="lbl"></span>
                                        </label>
                                    </th>
                                    <th>Tên Tòa Nhà</th>
                                    <th>Địa Chỉ</th>
                                    <th>Số Tầng Hầm</th>
                                    <th>Tên Quản Lý</th>
                                    <th>Số Điện Thoại Quản Lý</th>
                                    <th>Diện Tích Sàn</th>
                                    <th>Diện Tích Trống</th>
                                    <th>Diện Tích Thuê</th>
                                    <th>Thoa Tác</th>
                                </tr>
                                </thead>

                                <tbody id="BoxBuildingList">
                                <c:forEach var="item" items="${buildingList}" >
                                        <tr id="row_${item.id}">
                                                <td class="center">
                                                    <label class="pos-rel">
                                                        <input type="checkbox" class="ace" name="checkList"  value="${item.id}"/>
                                                        <span class="lbl"></span>
                                                    </label>
                                                </td>

                                                <td>${item.name}</td>
                                                <td>${item.address}</td>
                                                <td>${item.numberOfBasement}</td>
                                                <td>${item.managerName}</td>
                                                <td>${item.managerPhone}</td>
                                                <td>${item.floorArea}</td>
                                                <td>${item.floorArea}</td>
                                                <td>${item.rentArea}</td>

                                        <td>
                                            <div class="hidden-sm hidden-xs btn-group">
                                                        <button class="btn btn-xs btn-success" title="Giao toa nha" onclick="assignmentBuilding(${item.id})">
                                                            <i class="ace-icon fa fa-check bigger-120"></i>
                                                        </button>

                                                        <a class="btn btn-xs btn-info" title="Sửa Tòa Nhà" href="/admin/building-edit-${item.id}">
                                                            <i class="ace-icon fa fa-pencil bigger-120"></i>
                                                        </a>

                                                        <button class="btn btn-xs btn-danger" title="Xoa toa nha" onclick="deleteBuilding(${item.id})">
                                                            <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                                        </button>
                                            </div>
                                         </td>
                                    </tr>
                                </c:forEach>

                                </tbody>
                            </table>
                        </div>
                        <!-- /.span -->
                    </div>
                </div>
            </div>
            <!-- /.page-content -->
        </div>
    </div>
    <!-- /.main-content -->


<div class="modal fade" id="assignmentBuildingModal" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    &times;
                </button>
                <h4 class="titel-modal">Modal Header</h4>
            </div>
            <div class="modal-body">
                <table
                        class="table table-striped table-bordered table-hover"
                        id="staffList"
                >
                    <thead>
                    <tr>
                        <th class="center">Chọn</th>
                        <th class="center">Tên Nhân Viên</th>
                    </tr>
                    </thead>

                    <tbody>

                    </tbody>
                </table>
                <input type="hidden" id="buildingId" name="buildingId" value="" />
            </div>
                <div class="modal-footer">
                    <button
                            type="button"
                            class="btn ntn-default"
                            id="btnassignmentBuilding"
                    >
                        Giao Tòa Nhà
                    </button>
                    <button type="button" class="btn ntn-default" data-dismiss="modal">
                        close
                    </button>
                </div>
        </div>
    </div>
</div>

    <script>

        function loadStaffId(buildingId){
            $.ajax({
                type : "GET",
                url:'/api/building/staffs/' + buildingId,
                dataType:"json",
                success:function(response){
                    var row = '';
                    $.each(response.data,function(index,item){
                        row += '<tr>';
                        row += '<td class="text-center"><input type="checkbox" value="' + item.staffId + '" id="checkbox_' + item.staffId + '" class="check-box-element"' + (item.checked == "Checked" ? ' checked' : '' ) + '/></td>';
                        row += '<td class="text-center" >' + item.fullName + '</td>';
                        row += '</tr>'
                    });
                    $('#staffList tbody').html(row);
                },
            });
        }
        function assignmentBuilding(buildingId){
            $('#assignmentBuildingModal').modal();
            loadStaffId(buildingId);
            $('#buildingId').val(buildingId);
        }

        function apiAssignment(data){
            $.ajax({
                type : "POST",
                url : "/api/building/assignment",
                data : JSON.stringify(data),
                contentType: "application/json",
                success: function(){
                    console.log("success");
                    window.location.href = "/admin/building-list";
                },
                errors : function(){
                    console.log("errors");
                }
            });
        }
        $('#btnassignmentBuilding').click( function(e){
            e.preventDefault();
            let data = {};
            data["buildingId"] = $('#buildingId').val();
            var staffs = $('#staffList').find("tbody input[type = checkbox]:checked").map(function (){
                return $(this).val();
            }).get();
            data["staffs"] = staffs;
            if(data["staffs"] != ''){
                apiAssignment(data);
            }

        });

        $('#btnSearchBuilding').click(function(e) {
            e.preventDefault();
            let formData = $('#listForm').serialize();
            $.ajax({
                url: '/api/building-search',
                type: 'GET',
                data: formData,
                success: function(response) {
                    $('#BoxBuildingList').html($(response).find('#BoxBuildingList').html());
                }
            });
        });

        function deleteBuilding(buildingId){
            $.ajax({
               type : "DELETE",
               url : '/api/building/delete/' + buildingId ,
                success : function(response){
                    console.log("success");
                    $('#row_'+buildingId).remove();
                    alert("Da xoa thanh cong");
                },
                error : function(response){
                   console.log("errors")
                }
            });
        }


    </script>
</div>
</body>
</html>
