package org.qydata.controller;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.qydata.entity.Customer;
import org.qydata.entity.Dept;
import org.qydata.entity.User;
import org.qydata.regex.RegexUtil;
import org.qydata.service.CustomerService;
import org.qydata.service.DeptService;
import org.qydata.service.UserService;
import org.qydata.tools.ExcelUtil;
import org.qydata.util.ExportExcel;
import org.qydata.util.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jonhn on 2016/11/8.
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {

    private final Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private CustomerService customerService;

    @Autowired
    private DeptService deptService;

    @Autowired
    private UserService userService;

    /**
     * 添加新客户时验证账户名是否已存在
     * @param authId
     * @param response
     */
    @RequestMapping(value = "/findCustomerByAuthId/{authId}")
    public void findCustomerByAuthIdAdd(@PathVariable("authId") String authId,HttpServletResponse response){
        Customer customer = customerService.findByAuthId(authId);
        PrintWriter out = null;
        try {
            out = response.getWriter();
            if(customer!=null){
                out.print("yes");
            }else{
                out.print("no");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //添加账号View
    @RequestMapping(value = "/addCustomerAccountView/{companyId}")
    public String addCustomerAccountView(@PathVariable String companyId,Model model){
        model.addAttribute("companyId",companyId);
        return "customer/addCustomerAccount";
    }

    //添加账号Action
    @RequestMapping(value = "/addCustomerAccountAction")
    public String addCustomerAccountAction(String companyId,String authId,RedirectAttributes model){

        if(RegexUtil.isNull(companyId)){
            return "redirect:/customer/addCustomerAccountView/"+companyId;
        }
        if(RegexUtil.isNull(authId)){
            model.addFlashAttribute("CustomerMessageAuthId","请输入账户!");
            return "redirect:/customer/addCustomerAccountView/"+companyId;
        }
        if(!RegexUtil.stringCheck(authId)){
            model.addFlashAttribute("authId",authId);
            model.addFlashAttribute("CustomerMessageAuthId","账户格式输入不正确!");
            return "redirect:/customer/addCustomerAccountView/"+companyId;
        }
        try{
            boolean flag = customerService.insertCustomerAccount(companyId,authId);
            if (!flag) {
                model.addFlashAttribute("msg","对不起，添加失败，请检查你的输入！");
                return "redirect:/customer/addCustomerViewCommon";
            }
        }catch (Exception e){
            e.printStackTrace();
            model.addFlashAttribute("msg","对不起，添加失败，请检查你的输入！");
            return "redirect:/customer/addCustomerViewCommon";
        }
        return "redirect:/company/findAllCustomerAccountByCompanyId/"+companyId;

    }

    //通过authId查找账号详细信息
    @RequestMapping(value = "/findCustomerDetailInfo/{authId}")
    public String findCustomerDetailInfo(@PathVariable String authId,Model model){
        Customer customer = customerService.findByAuthId(authId);
        model.addAttribute("customer",customer);
        return "/customerBalanceLog/detailCustomerInfo";
    }



    //（暂时没用到）
    @RequestMapping(value = ("/addCustomerOnlyDeptView/{companyId}"))
    public String addCustomerViewCommon(Model model,HttpServletRequest request,@PathVariable String companyId){
        User user = (User)request.getSession().getAttribute("userInfo");
        List<Dept> deptList = user.getDept();
        model.addAttribute("companyId",companyId);
        model.addAttribute("deptList",deptList);
        return "customer/addCustomerOnlyDept";
    }

    //（暂时没用到）
    @RequestMapping(value = ("/addCustomerAllDeptView/{companyId}"))
    public String addCustomerViewSuper(Model model,HttpServletRequest request,@PathVariable String companyId){
        List<Dept> deptList = null;
        try {
            deptList = deptService.findAllDept();
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("companyId",companyId);
        model.addAttribute("deptList",deptList);
        return "customer/addCustomerAllDept";
    }

    //（暂时没用到）
    @RequestMapping(value = ("/addCustomerOnlyDeptAction"))
    public String insertCustomerByDeptNo(String companyId, String authId, String deptId, RedirectAttributes model){

        if(RegexUtil.isNull(companyId)){
            return "redirect:/customer/addCustomerOnlyDeptView/"+companyId;
        }
        if(RegexUtil.isNull(authId)){
            model.addFlashAttribute("CustomerMessageAuthId","请输入账户!");
            return "redirect:/customer/addCustomerOnlyDeptView/"+companyId;
        }
        if(!RegexUtil.stringCheck(authId)){
            model.addFlashAttribute("authId",authId);
            model.addFlashAttribute("CustomerMessageAuthId","账户格式输入不正确!");
            return "redirect:/customer/addCustomerOnlyDeptView/"+companyId;
        }
        if(RegexUtil.isNull(deptId)){
            model.addFlashAttribute("authId",authId);
            model.addFlashAttribute("CustomerMessageDeptId","请选择所属部门!");
            return "redirect:/customer/addCustomerOnlyDeptView/"+companyId;
        }
        try{
            boolean flag = customerService.insertCustomer(companyId,authId,deptId);
            if (!flag) {
                model.addFlashAttribute("msg","对不起，添加失败，请检查你的输入！");
                return "redirect:/customer/addCustomerViewCommon";
            }
        }catch (Exception e){
            e.printStackTrace();
            model.addFlashAttribute("msg","对不起，添加失败，请检查你的输入！");
            return "redirect:/customer/addCustomerViewCommon";
        }
        return "redirect:/company/findAllCustomerAccountByCompanyId/"+companyId;
    }

    //（暂时没用到）
    @RequestMapping(value = ("/addCustomerAllDeptAction"))
    public String insertCustomer(String companyId, String authId, String deptId, RedirectAttributes model){
        if(RegexUtil.isNull(companyId)){
            return "redirect:/customer/addCustomerAllDeptView/"+companyId;
        }
        if(RegexUtil.isNull(authId)){
            model.addFlashAttribute("CustomerMessageAuthId","请输入账户!");
            return "redirect:/customer/addCustomerAllDeptView/"+companyId;
        }
        if(!RegexUtil.stringCheck(authId)){
            model.addFlashAttribute("authId",authId);
            model.addFlashAttribute("CustomerMessageAuthId","账户格式输入不正确!");
            return "redirect:/customer/addCustomerAllDeptView/"+companyId;
        }
        if(RegexUtil.isNull(deptId)){
            model.addFlashAttribute("authId",authId);
            model.addFlashAttribute("CustomerMessageDeptId","请选择所属部门!");
            return "redirect:/customer/addCustomerAllDeptView/"+companyId;
        }

        try{
            boolean flag = customerService.insertCustomer(companyId,authId,deptId);
            if (!flag) {
                model.addFlashAttribute("msg","对不起，添加失败，请检查你的输入！");
                return "redirect:/customer/addCustomerViewSuper";
            }
        }catch (Exception e){
            e.printStackTrace();
            model.addFlashAttribute("msg","对不起，添加失败，请检查你的输入！");
            return "redirect:/customer/addCustomerViewSuper";
        }
        return "redirect:/company/findAllCustomerAccountByCompanyId/"+companyId;
    }










    @RequestMapping("/list1")
    public String list1(){
        return "/customer/list";
    }
    @RequestMapping(value = "/list")
    @ResponseBody
    public String list(HttpServletRequest request){
        String aoData = request.getParameter("aoData");
        JSONArray jsonarray = JSONArray.fromObject(aoData);
        System.out.println(aoData);
        String sEcho = null;
        int iDisplayStart = 0; // 起始索引
        int iDisplayLength = 0; // 每页显示的行数
        int iColumns = 4;
        String sColumns = "";
        int mDataProp_0 = 0;
        int mDataProp_1 = 1;
        int mDataProp_2 = 2;
        int mDataProp_3 = 3;
        int iSortCol_0 = 0;
        String sSortDir_0 = "asc";
        int iSortingCols = 1;
        boolean bSortable_0 = true;
        boolean bSortable_1 = true;
        boolean bSortable_2 = true;
        boolean bSortable_3 = true;
        for (int i = 0; i < jsonarray.size(); i++) {
            JSONObject obj = (JSONObject) jsonarray.get(i);
            if (obj.get("name").equals("sEcho"))
                sEcho = obj.get("value").toString();

            if (obj.get("name").equals("iDisplayStart"))
                iDisplayStart = obj.getInt("value");

            if (obj.get("name").equals("iDisplayLength"))
                iDisplayLength = obj.getInt("value");
        }
        System.out.println(sEcho);
        System.out.println(iDisplayStart);
        System.out.println(iDisplayLength);
        List<User> userList = userService.findAllUser(iDisplayStart,iDisplayLength);
        JSONArray jsonArray =JSONArray.fromObject(userList);
        System.out.println(jsonArray.toString());
        JSONObject getObj = new JSONObject();
        getObj.put("sEcho", sEcho);// 不知道这个值有什么用,有知道的请告知一下
        getObj.put("iTotalRecords", 27);//实际的行数
        getObj.put("iTotalDisplayRecords", 27);//显示的行数,这个要和上面写的一样
        getObj.put("aaData",jsonArray);//要以JSON格式返回
        return getObj.toString();
    }

    @RequestMapping("/Excel")
    public String Excel(HttpServletRequest request,HttpServletResponse response){
        response.setContentType("octets/stream");
//      response.addHeader("Content-Disposition", "attachment;filename=test.xls");
        String excelName = "学生信息表";
        //转码防止乱码
        try {
            response.addHeader("Content-Disposition", "attachment;filename="+new String( excelName.getBytes("gb2312"), "ISO8859-1" )+".xls");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String[] headers = new String[]{"编号","姓名","年龄","性别"};
        try {
            OutputStream out = response.getOutputStream();
            ExportExcel.exportExcel(excelName,headers, getList(), out,"yyyy-MM-dd");
            out.close();
            System.out.println("excel导出成功！");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
    public List<Map<String,Object>> getList(){
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        for(int i = 0; i<100;i++){
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("number",1000+i);
            map.put("name", "张三"+i);
            int age = (int)(Math.random()*100);
            do{
                age = (int)(Math.random()*100);
            }while(age<10||age>15);
            map.put("age", age);
            map.put("sex", age%2==0?0:1);//获得随机性别
            list.add(map);
        }
        return list;
    }


    @RequestMapping(value="/download_project.do")
    public String download(HttpServletRequest request,HttpServletResponse response) throws IOException{
        String fileName="excel文件";
        //填充projects数据
        List<Project> projects=createData();
        List<Map<String,Object>> list=createExcelRecord(projects);
        String columnNames[]={"ID","项目名","销售人","负责人","所用技术","备注"};//列名
        String keys[]    =     {"id","name","saler","principal","technology","remarks"};//map中的key
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ExcelUtil.createWorkBook(list,keys,columnNames).write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] content = os.toByteArray();
        InputStream is = new ByteArrayInputStream(content);
        // 设置response参数，可以打开下载页面
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".xls").getBytes(), "iso-8859-1"));
        ServletOutputStream out = response.getOutputStream();
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            // Simple read/write loop.
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (final IOException e) {
            throw e;
        } finally {
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
        }
        return null;
    }

    private List<Project> createData() {
        // TODO Auto-generated method stub
        //自己实现
        List<Project> projects = new ArrayList<>();
//        for (int i=0;i<50;i++){
//            Project project = new Project();
//            project.setId(i);
//            project.setName(i+"");
//            project.setRemarks(i+"");
//            project.setTechnology(i+"");
//            projects.add(project);
//        }
        return projects;
    }

    private List<Map<String, Object>> createExcelRecord(List<Project> projects) {
        List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sheetName", "sheet1");
        listmap.add(map);
        Project project=null;
        for (int j = 0; j < projects.size(); j++) {
            project=projects.get(j);
            Map<String, Object> mapValue = new HashMap<String, Object>();
            mapValue.put("id", project.getId());
            mapValue.put("name", project.getName());
            mapValue.put("technology", project.getTechnology());
            mapValue.put("remarks", project.getRemarks());
            listmap.add(mapValue);
        }
        return listmap;
    }


}
