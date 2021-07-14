<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form class="form-horizontal" role="form" action="CategoryServlet?do=addCategory" method="post">
				
				<div class="form-group">
					<label class="control-label col-md-2">类别ID：</label>
					<div class="col-md-3">
						<input type="text" class="form-control" placeholder="请输入ID" name="categoryID" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-2">类别名称：</label>
					<div class="col-md-3">
						<input type="text" class="form-control" placeholder="请输入类别名称" name="categoryname" />
					</div>
				</div>
				
				

				<div class="form-group">
					<div class="col-md-2"></div>
					<div class="col-md-3">
						<button type="submit" class="btn btn-primary pull-right" style="margin-right: 10px;">添加</button>
						<button type="reset" class="btn btn-default pull-right" style="margin-right: 10px;">重置</button>
					</div>
				</div>

			</form>