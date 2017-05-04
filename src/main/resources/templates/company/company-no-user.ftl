

<#--新增公司-->
<#--  <div id="form_modal1" class="modal hide fade myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1" aria-hidden="true">
      <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
          <h3 id="myModalLabel1">请填写信息</h3>
      </div>
      <div class="modal-body">
          <form action="#" class="form-horizontal">
              <div class="control-group"></div>
              <div class="control-group"></div>
              <div id="error-alert"></div>
              <div class="control-group">
                  <label class="control-label">请输入公司名称<span class="required">*</span></label>
                  <div class="controls">
                      <input type="text" id="companyCustomerName" name="companyCustomerName" class="m-wrap medium">
                      <span id="companyNameMsg" class="help-line"></span>
                  </div>
              </div>
              <div class="control-group">
                  <label class="control-label">请输入账号<span class="required">*</span></label>
                  <div class="controls">
                      <input type="text" id="authId" name="authId" class="m-wrap medium">
                      <span id="authIdMsg" class="help-line"></span>
                      <span class="help-block">说明：只能有数字、字母、下划线组成</span>
                  </div>
              </div>
              <div class="control-group">
                  <label class="control-label">请选择合作公司<span class="required">*</span></label>
                  <div class="controls">
                      <select id="partnerId" name="partnerId" class="medium m-wrap" tabindex="1">
                          <option value="">请选择...</option>
                          <#if partnerList??>
                              <#list partnerList as partner>
                                  <option value="${partner.id}">${partner.name}</option>
                              </#list>
                          </#if>
                      </select>
                  </div>
              </div>
          </form>
      </div>
      <div class="modal-footer">
          <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
          <button class="btn black btn-primary" id="add-btn-black-btn-primary" type="button">提交</button>
      </div>
  </div>
-->
<#--  &lt;#&ndash;添加账号&ndash;&gt;
      <div id="form_modal_add_account" class="modal hide fade myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel_add_account" aria-hidden="true">
          <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
              <h3 id="myModalLabel_add_account">请填写信息</h3>
          </div>
          <div class="modal-body">
              <form action="#" class="form-horizontal">
                  <div class="control-group"></div>
                  <div class="control-group"></div>
                  <div id="error-alert-account"></div>
                  <div id="authId-account-controls" class="controls" style="display: none;"></div>
                  <div class="control-group">
                      <label class="control-label">请输入账号<span class="required">*</span></label>
                      <div class="controls">
                          <input type="text" id="authId-account" name="authId-account" class="m-wrap medium">
                          <span id="authId-accountMsg" class="help-line"></span>
                          <span class="help-block">说明：只能有数字、字母、下划线组成</span>
                      </div>
                  </div>
              </form>
          </div>
          <div class="modal-footer">
              <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
              <button class="btn black btn-primary" id="add-account-btn-black-btn-primary" type="button">提交</button>
          </div>
      </div>
-->
<#--&lt;#&ndash;添加Ip&ndash;&gt;
    <div id="form_modal_add_ip" class="modal hide fade myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel_add_ip" aria-hidden="true">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
            <h3 id="myModalLabel_add_ip">添加Ip</h3>
        </div>
        <div class="modal-body">
            <div class="control-group text-right">
                <a href="#" id="control-group-add-ip-href"><span><i class="icon-plus"></i>点击添加一栏</span></a>
            </div>
            <div class="control-group"></div>
            <div class="control-group"></div>
            <div id="error_alert_add_ip"></div>
            <div id="add_ip_customerId" class="controls" style="display: none;"></div>
            <div class="control-group text-center" id="control-group-add-ip">

            </div>
        </div>
        <div class="modal-footer">
            <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
            <button class="btn black btn-primary" id="add-ip-btn-black-btn-primary" type="button">提交</button>
        </div>
    </div>
-->
<#-- &lt;#&ndash;批量添加产品&ndash;&gt;
     <div id="form_modal_add_api" class="modal hide fade myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabell_add_api" aria-hidden="true">
         <div class="modal-header">
             <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
             <h3 id="myModalLabell_add_api">分配产品权限</h3>
         </div>
         <div class="modal-body">

         </div>
         <div class="modal-footer">
             <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
             <button class="btn black btn-primary" id="add-ip-btn-black-btn-primary" type="button">提交</button>
         </div>
     </div>
-->