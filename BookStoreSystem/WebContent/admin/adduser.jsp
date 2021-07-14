<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%>
<form class="form-horizontal" role="form" action="UserServlet?do=addUser" method="post">
				<div class="form-group">
					<label class="control-label col-md-2">姓名：</label>
					<div class="col-md-3">
						<input type="text" class="form-control" placeholder="请输入姓名" name="name" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-2">性别：</label>
					<div class="col-md-3 radio">
						<label>
						<input type="radio" name="gender" value="男"/>男&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="gender" value="女"/>女
						</label>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-2">年龄：</label>
					<div class="col-md-3">
						<input type="text" class="form-control" placeholder="请输入年龄" name="age" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-2">用户名：</label>
					<div class="col-md-3">
						<input type="text" class="form-control" placeholder="请输入用户名" name="username" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-2">密码：</label>
					<div class="col-md-3">
						<input type="password" class="form-control" placeholder="请输入密码" name="password" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-2">再次输入密码：</label>
					<div class="col-md-3">
						<input type="password" class="form-control" placeholder="请再次输入密码" name="password2"/>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-2">管理员：</label>
					<div class="col-md-3 radio">
						<label>
						<input type="radio" name="admin" value="1"/>是&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="admin" value="0"/>否
						</label>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-2">电话：</label>
					<div class="col-md-3">
						<input type="text" class="form-control" placeholder="请输入电话" name="phone" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-2">住址：</label>
					<div class="col-md-3">
						<input type="text" class="form-control" placeholder="请输入住址" name="adress"/>
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