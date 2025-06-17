<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<html>
<head>
    <title>Thêm Tòa Nhà</title>
</head>
<body>
<div class="main-content" >
  <script type="text/javascript">
    try {
      ace.settings.check("main-container", "fixed");
    } catch (e) {}
  </script>

<%--  <div id="sidebar" class="sidebar responsive">--%>
<%--    <script type="text/javascript">--%>
<%--      try {--%>
<%--        ace.settings.check("sidebar", "fixed");--%>
<%--      } catch (e) {}--%>
<%--    </script>--%>

<%--    <div class="sidebar-shortcuts" id="sidebar-shortcuts">--%>
<%--      <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">--%>
<%--        <button class="btn btn-success">--%>
<%--          <i class="ace-icon fa fa-signal"></i>--%>
<%--        </button>--%>

<%--        <button class="btn btn-info">--%>
<%--          <i class="ace-icon fa fa-pencil"></i>--%>
<%--        </button>--%>

<%--        <button class="btn btn-warning">--%>
<%--          <i class="ace-icon fa fa-users"></i>--%>
<%--        </button>--%>

<%--        <button class="btn btn-danger">--%>
<%--          <i class="ace-icon fa fa-cogs"></i>--%>
<%--        </button>--%>
<%--      </div>--%>

<%--      <div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">--%>
<%--        <span class="btn btn-success"></span>--%>

<%--        <span class="btn btn-info"></span>--%>

<%--        <span class="btn btn-warning"></span>--%>

<%--        <span class="btn btn-danger"></span>--%>
<%--      </div>--%>
<%--    </div>--%>
<%--    <!-- /.sidebar-shortcuts -->--%>

<%--    <ul class="nav nav-list">--%>
<%--      <li class="active">--%>
<%--        <a href="index.html">--%>
<%--          <i class="menu-icon fa fa-tachometer"></i>--%>
<%--          <span class="menu-text"> Quản Lý Tòa Nhà</span>--%>
<%--        </a>--%>

<%--        <b class="arrow"></b>--%>
<%--      </li>--%>
<%--    </ul>--%>
<%--    <!-- /.nav-list -->--%>

<%--    <div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">--%>
<%--      <i--%>
<%--              class="ace-icon fa fa-angle-double-left"--%>
<%--              data-icon1="ace-icon fa fa-angle-double-left"--%>
<%--              data-icon2="ace-icon fa fa-angle-double-right"--%>
<%--      ></i>--%>
<%--    </div>--%>

<%--    <script type="text/javascript">--%>
<%--      try {--%>
<%--        ace.settings.check("sidebar", "collapsed");--%>
<%--      } catch (e) {}--%>
<%--    </script>--%>
<%--  </div>--%>

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
          <li class="active">Sửa xóa tòa nhà</li>
        </ul>
        <!-- /.breadcrumb -->

        <!-- /.nav-search -->
      </div>

      <div class="page-content">
        <!-- /.ace-settings-container -->

        <div class="page-header">
          <h1 style="font-family: 'Times New Roman', Times, serif">
            Sửa Hoặc Thêm Tòa Nhà
          </h1>
        </div>
        <!-- /.page-header -->
        <div
                class="row"
                style="font-family: 'Times New Roman', Times, serif"
        >
        <form:form  modelAttribute="buildingEdit" id="listForm" method="GET" action="/admin/building-edit">
          <div class="col-xs-12">
            <form class="form-horizontal" role="form" >
              <div class="form-group">
                <label class="col-sm-3">Tên Tòa Nhà</label>
                <div class="col-sm-9">
                  <form:input class="form-group" path="name" id ="name"/>
                </div>
              </div>

              <div class="form-group">
                <label class="col-sm-3">Quận</label>
                <form:select class="form-control" path="district">
                    <form:option value="">---Chọn Quận---</form:option>
                        <form:options items="${districts}" />
                </form:select>
              </div>

              <div class="form-group">
                <label class="col-sm-3">Phường</label>
                <div class="col-sm-9">
                  <form:input class="form-group" path="ward"/>
                </div>
              </div>

              <div class="form-group">
                <label class="col-xs-3">Đường</label>
                <div class="col-xs-9">
                  <form:input class="form-group" path="street"/>

                </div>
              </div>

              <div class="form-group">
                <label class="col-xs-3">Kết Cấu</label>
                <div class="col-xs-9">
                  <form:input class="form-group" path="structure"/>

                </div>
              </div>
              <div class="form-group">
                <label class="col-xs-3">Số Tầng Hầm</label>
                <div class="col-xs-9">
                  <form:input class="form-group" path="numberOfBasement"/>

                </div>
              </div>
              <div class="form-group">
                <label class="col-xs-3">Diện Tích Sàn</label>
                <div class="col-xs-9">
                  <form:input class="form-group" path="floorArea"/>

                </div>
              </div>

              <div class="form-group">
                <label class="col-xs-3">Hướng</label>
                <div class="col-xs-9">
                  <form:input class="form-group" path="direction"/>

                </div>
              </div>

              <div class="form-group">
                <label class="col-xs-3">Hạng</label>
                <div class="col-xs-9">
                  <form:input class="form-group" path="level" />
                </div>
              </div>

              <div class="form-group">
                <label class="col-xs-3">Diện Tích Thuê</label>
                <div class="col-xs-9">
                  <form:input class="form-group" path="rentArea"/>
                </div>
              </div>

              <div class="form-group">
                <label class="col-xs-3">Giá Thuê</label>
                <div class="col-xs-9">
                  <form:input class="form-group" path="rentPrice" />
                </div>
              </div>

              <div class="form-group">
                <label class="col-xs-3">Mô Tả Giá</label>
                <div class="col-xs-9">
                  <input
                          class="form-control"
                          type="text"
                          id="rentpricedescription"
                          name="rentpricedescription"
                  />
                </div>
              </div>



              <div class="form-group">
                <label class="col-xs-3">Tên Quản Lý</label>
                <div class="col-xs-9">
                  <form:input class="form-group" path="managerName" />
                </div>
              </div>

              <div class="form-group">
                <label class="col-xs-3">Số Điện Thoại Quản Lý</label>
                <div class="col-xs-9">
                  <form:input class="form-group" path="managerPhone" />
                </div>
              </div>

              <div class="form-group">
                <label class="col-xs-3">Loại Tòa Nhà</label>
                <div class="col-xs-9">
                    <div class="col-sm-6"  id="listType">
                        <form:checkboxes items="${typeCodes}" path="typeCode"  />
                    </div>
                </div>
              </div>
              <div class="form-group">
                <label class="col-xs-3">Ghi Chú</label>
                <div class="col-xs-9">
                  <input
                          class="form-control"
                          type="text"
                          id="name"
                          name="name"
                  />
                </div>
              </div>
              <div class="form-group">
                <label class="col-xs-3"></label>
                <div class="col-xs-9">
                  <c:if test="${not empty buildingEdit.id}">
                       <button
                          type="button"
                          class="btn btn-success"
                          id="btnAddOrUpdateBuilding"
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
                    Sửa Tòa Nhà
                  </button>
                       <button type="button" class="btn btn-danger" id="btnCancel" >
                    <svg
                            xmlns="http://www.w3.org/2000/svg"
                            width="16"
                            height="16"
                            fill="currentColor"
                            class="bi bi-trash3"
                            viewBox="0 0 16 16"
                    >
                      <path
                              d="M6.5 1h3a.5.5 0 0 1 .5.5v1H6v-1a.5.5 0 0 1 .5-.5M11 2.5v-1A1.5 1.5 0 0 0 9.5 0h-3A1.5 1.5 0 0 0 5 1.5v1H1.5a.5.5 0 0 0 0 1h.538l.853 10.66A2 2 0 0 0 4.885 16h6.23a2 2 0 0 0 1.994-1.84l.853-10.66h.538a.5.5 0 0 0 0-1zm1.958 1-.846 10.58a1 1 0 0 1-.997.92h-6.23a1 1 0 0 1-.997-.92L3.042 3.5zm-7.487 1a.5.5 0 0 1 .528.47l.5 8.5a.5.5 0 0 1-.998.06L5 5.03a.5.5 0 0 1 .47-.53Zm5.058 0a.5.5 0 0 1 .47.53l-.5 8.5a.5.5 0 1 1-.998-.06l.5-8.5a.5.5 0 0 1 .528-.47M8 4.5a.5.5 0 0 1 .5.5v8.5a.5.5 0 0 1-1 0V5a.5.5 0 0 1 .5-.5"
                      ></path>
                    </svg>
                    Hủy thao Tác
                  </button>
                  </c:if>

                  <c:if test="${empty buildingEdit.id}">
                            <button
                          type="button"
                          class="btn btn-success"
                          id="btnAddOrUpdateBuilding"
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
                    Thêm Tòa Nhà
                  </button>
                      <button type="button" class="btn btn-danger" id="btnCancel" >
                    <svg
                            xmlns="http://www.w3.org/2000/svg"
                            width="16"
                            height="16"
                            fill="currentColor"
                            class="bi bi-trash3"
                            viewBox="0 0 16 16"
                    >
                      <path
                              d="M6.5 1h3a.5.5 0 0 1 .5.5v1H6v-1a.5.5 0 0 1 .5-.5M11 2.5v-1A1.5 1.5 0 0 0 9.5 0h-3A1.5 1.5 0 0 0 5 1.5v1H1.5a.5.5 0 0 0 0 1h.538l.853 10.66A2 2 0 0 0 4.885 16h6.23a2 2 0 0 0 1.994-1.84l.853-10.66h.538a.5.5 0 0 0 0-1zm1.958 1-.846 10.58a1 1 0 0 1-.997.92h-6.23a1 1 0 0 1-.997-.92L3.042 3.5zm-7.487 1a.5.5 0 0 1 .528.47l.5 8.5a.5.5 0 0 1-.998.06L5 5.03a.5.5 0 0 1 .47-.53Zm5.058 0a.5.5 0 0 1 .47.53l-.5 8.5a.5.5 0 1 1-.998-.06l.5-8.5a.5.5 0 0 1 .528-.47M8 4.5a.5.5 0 0 1 .5.5v8.5a.5.5 0 0 1-1 0V5a.5.5 0 0 1 .5-.5"
                      ></path>
                    </svg>
                    Hủy thao Tác
                    </button>
                  </c:if>

                </div>
               </div>
              <form:hidden path="id" id="buildingId"/>
            </form>
          </div>
          </form:form>

        </div>
      </div>
    </div>
    <!-- /.page-content -->
  </div>
</div>

<script>
    function addOrUpdate(data){
      $.ajax({
        type : "POST",
        url : "/api/building/addOrUpdate",
        data : JSON.stringify(data),
        contentType : "application/json",
        success:function() {
          console.log("success");
          window.location.href = "/admin/building-list";
        },
        errors :  function(){
          console.log("errors");
        }
      })
    }

    $('#btnAddOrUpdateBuilding').click(function(){
        let data = {};
        var typeCode = [] ;
        var form = $('#listForm').serializeArray();
        $.each(form , function(index,item){
            if(item.name != "typeCode"){
              data[""+item.name+""] = item.value;
            }
            else{
              typeCode.push(item.value);
            }
        });
        data["typeCode"] = typeCode;
        data["name"] = $('#name').val();
        if(typeCode != "" && data["name"] != "" && data["rentPrice"] != ""){
          addOrUpdate(data);
        }
    });


    $("#btnCancel").click(function () {
        window.location.href="/admin/building-list";
    });

</script>
</body>
</html>
