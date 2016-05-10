package com.goldcow.emanage.util.system.SysCtrlPermission;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.goldcow.emanage.util.gen.entity.SysCtrlPermissionCtrl;
/**
 * 解析按组别存储控件的xml的解析工具类
 * 
 * @author RiverYao
 * @version v1.2
 * @since 2015-05-20
 */
public class SysCtrlPermission {
	// 控件默认可写性为可写
	private static String DEFAULT_READONLY = "true";
	// 控件默认可见性为可见,ture为可见
	private static String DEFAULT_HIDDEN = "true";
	// 控件默认必填性为false
	private static String DEFAULT_WRITTEN = "false";

	/**
	 * 验证当前的XML文件地址是否与指定的PageName和PageGroupID相符
	 * 
	 * @param FilePath 文件存储的全路径
	 * @param PageName 需要设置权限的页面名称
	 * @param PageGroupID 该页面的权限所属组的ID
	 * */
	private static Boolean CheckXMLForPageNameAndPageGroupID(String FilePath, String PageName, String PageGroupID) {
		String filePath = FilePath.trim();
		String fileName=filePath.substring(filePath.lastIndexOf("\\")+1);
		String filePageGroupID= fileName.substring(0,fileName.indexOf("_"));
		String filePageName = fileName.substring(fileName.indexOf("_")+1, fileName.indexOf("."));
		if(PageName.equals(filePageName)&&PageGroupID.equals(filePageGroupID)){
			return true;
		}
		else{
			return false;
		}
	}

	/**
	 * 根据某页面的控件的权限生成XML的权限记录文档
	 * 
	 * @param FilePath 文件存储的全路径
	 * @param PageName 需要设置权限的页面名称
	 * @param PageGroupID 该页面的权限所属组的ID
	 * @param SysCtrlPermissionCtrlList 控件权限的数组
	 * @return Boolean 返回操作结果
	 * */
	public static Boolean CreateXMLPermissionForCtrls(String FilePath, String PageName, String PageGroupID, List<SysCtrlPermissionCtrl> SysCtrlPermissionCtrlList) {
		// 检查文件是否匹配
		if (!CheckXMLForPageNameAndPageGroupID(FilePath, PageName, PageGroupID)) {
			return false;
		}
		Document document = DocumentHelper.createDocument();
		// ROOT标签
		Element Page_Element = document.addElement("Page");
		// 页面公共信息标签
		Element PageInfo_Element = Page_Element.addElement("PageInfo");
		// 页面名称
		Element PageName_Element = PageInfo_Element.addElement("PageName");
		PageName_Element.setText(PageName);
		// 页面圈子按组ID
		Element PageGroupID_Element = PageInfo_Element.addElement("PageGroupID");
		PageGroupID_Element.setText(PageGroupID);
		// 页面控件权限标签
		Element PageCtrls_Element = Page_Element.addElement("PageCtrls");
		// 遍历每一个需要设置权限的控件
		for (int i = 0; i < SysCtrlPermissionCtrlList.size(); i++) {
			if (SysCtrlPermissionCtrlList.get(i).getCtrlID() != null && !SysCtrlPermissionCtrlList.get(i).getCtrlID().equals("")) {
				// 控件ID
				Element Ctrl_Element = PageCtrls_Element.addElement("Ctrl");
				Ctrl_Element.addAttribute("id", SysCtrlPermissionCtrlList.get(i).getCtrlID());
				// 控件所在控件组的外层DIV的ID
				Element CtrlDivID_Element = Ctrl_Element.addElement("CtrlDivID");
				CtrlDivID_Element.setText(SysCtrlPermissionCtrlList.get(i).getCtrlDivID());
				// 控件类型
				Element CtrlType_Element = Ctrl_Element.addElement("CtrlType");
				CtrlType_Element.setText(SysCtrlPermissionCtrlList.get(i).getCtrlType());
				// 控件的可写性
				Element ReadOnly_Element = Ctrl_Element.addElement("ReadOnly");
				if (SysCtrlPermissionCtrlList.get(i).getReadOnly() == null || SysCtrlPermissionCtrlList.get(i).getReadOnly().equals("")) {
					ReadOnly_Element.setText(DEFAULT_READONLY);
				} else {
					ReadOnly_Element.setText(SysCtrlPermissionCtrlList.get(i).getReadOnly().toString());
				}
				// 控件的可见性
				Element Hidden_Element = Ctrl_Element.addElement("Hidden");
				if (SysCtrlPermissionCtrlList.get(i).getHidden() == null || SysCtrlPermissionCtrlList.get(i).getHidden().equals("")) {
					Hidden_Element.setText(DEFAULT_HIDDEN);
				} else {
					Hidden_Element.setText(SysCtrlPermissionCtrlList.get(i).getHidden());
				}
				// 控件的必填性
				Element Written_Element = Ctrl_Element.addElement("Written");
				if (SysCtrlPermissionCtrlList.get(i).getWritten() == null || SysCtrlPermissionCtrlList.get(i).getWritten().equals("")) {
					Written_Element.setText(DEFAULT_WRITTEN);
				} else {
					Written_Element.setText(SysCtrlPermissionCtrlList.get(i).getWritten());
				}
			}
		}
		try {
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setIndentSize(4);
			format.setNewlines(true);
			format.setTrimText(false);
			format.setPadText(true);
			format.setEncoding("UTF-8");
			XMLWriter output = new XMLWriter(new FileWriter(new File(FilePath)), format);
			output.write(document);
			output.close();
			System.out.println(FilePath+"创建成功");
			return true;
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return false;
		}

	}

	/**
	 * 根据文件路径读出组页面配置XML
	 * 
	 * @param FilePath 文件存储的全路径
	 * @param PageName 需要设置权限的页面名称
	 * @param PageGroupID 该页面的权限所属组的ID
	 * @return List<SysCtrlPermissionCtrl>数组
	 * */
	public static List<SysCtrlPermissionCtrl> ReadXMLPermissionForCtrls(String FilePath, String PageName, String PageGroupID) {
		SAXReader reader = new SAXReader();
		Document document = null;
		// 验证文件是否相符
		if (!CheckXMLForPageNameAndPageGroupID(FilePath, PageName, PageGroupID)) {
				return null;
		}
		try {
			document = reader.read(new File(FilePath));
		} catch (Exception e) {
			System.out.println("读取文件异常:\n"+e);
			return null;
		}
		// 获得根节点元素对象
		Element RootElement = document.getRootElement();
		// 获得根节点下为"PageCtrls"的子元素
		Element PageCtrls_Element = RootElement.element("PageCtrls");
		// 获得pagectrls下名为"Ctrl"的子元素
		List<Element> ElementList = PageCtrls_Element.elements("Ctrl");

		List<SysCtrlPermissionCtrl> SysCtrlPermissionCtrlList = new ArrayList();

		for (Element e : ElementList) {
			SysCtrlPermissionCtrl SysCtrlPermissionCtrl = new SysCtrlPermissionCtrl();
			SysCtrlPermissionCtrl.setCtrlID(e.attributeValue("id"));
			SysCtrlPermissionCtrl.setCtrlDivID(e.elementText("CtrlDivID"));
			SysCtrlPermissionCtrl.setCtrlType(e.elementText("CtrlType"));
			if (e.elementText("Hidden") == null || e.elementText("Hidden").equals("")) {
				SysCtrlPermissionCtrl.setHidden(DEFAULT_HIDDEN);
			} else {
				SysCtrlPermissionCtrl.setHidden(e.elementText("Hidden"));
			}
			if (e.elementText("ReadOnly") == null || e.elementText("ReadOnly").equals("")) {
				SysCtrlPermissionCtrl.setReadOnly(DEFAULT_READONLY);
			} else {
				SysCtrlPermissionCtrl.setReadOnly(e.elementText("ReadOnly"));
			}
			if (e.elementText("Written") == null || e.elementText("Written").equals("")) {
				SysCtrlPermissionCtrl.setWritten(DEFAULT_WRITTEN);
			} else {
				SysCtrlPermissionCtrl.setWritten(e.elementText("Written"));
			}
			SysCtrlPermissionCtrlList.add(SysCtrlPermissionCtrl);
		}
		return SysCtrlPermissionCtrlList;
	}

}
