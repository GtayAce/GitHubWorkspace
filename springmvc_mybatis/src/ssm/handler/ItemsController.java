package ssm.handler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import ssm.po.ItemsCustom;
import ssm.po.ItemsQueryVo;
import ssm.service.ItemsService;

/**
 * 
 * <p>Title: ItemsController.java</p>  
 * <p>Description: 商品的Controller</p>
 * 
 * Controller 的返回参数
 * 1. ModelAndView
 *  	controller方法中定义ModelAndView对象并返回，对象中可添加model数据、指定view。
 * 2. String
 *  	1)只返回逻辑视图名,模型数据在形参中的Model举行补充了
 *   	2)真正视图名  = 前缀 + 逻辑视图名 + 后缀
 *    	3)redirect重定向
 *    		特定:Contrller方法返回结果重定向到一个url地址，如下商品修改提交后重定向到商品查询方法，参数无法带到商品查询方法中。
 *     	4)forward页面转发
 *     		通过forward进行页面转发,浏览器URL不变,request可以共享
 * 3. void
 */

@Controller
//为了更好地对url进行管理,可以使用@RequestMapping("")定义一个根目录,最终的访问路径是根路径加上子路径
//窄化请求映射
@RequestMapping("/items")
public class ItemsController {
	@Autowired
	private ItemsService itemsService;
	
	@RequestMapping("/queryItems")
	public ModelAndView queryItems(HttpServletRequest request, ItemsQueryVo itemsQueryVo) throws Exception{
		System.out.println("进入了/queryItems.action");
		//测试forward后,request是否共享
		System.out.println(request.getParameter("id"));
		
		//当ItemsQueryVo=null的时候,将查询全部商品列表
		List<ItemsCustom> itemsList = itemsService.findItemsList(itemsQueryVo);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("itemsList",itemsList);
		modelAndView.setViewName("items/itemsList");
		return modelAndView;
	}
	
	//商品信息修改提示页面
	//@RequestMapping("/editItems")
	//限制http访问的方法
//	@RequestMapping(value="/editItems",method={RequestMethod.GET,RequestMethod.POST})
//	public ModelAndView editItem() throws Exception{
//		System.out.println("进入了/editItem.action");
//		ItemsCustom itemsCustom = itemsService.findItemsById(1);
//		
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.addObject("itemsCustom",itemsCustom);
//		modelAndView.setViewName("items/editItems");
//		return modelAndView;
//	}
	
	@RequestMapping(value="/editItems",method={RequestMethod.GET,RequestMethod.POST})
	/**
	 * 使用@RequestParam常用于处理简单类型的绑定。  
	 * value：参数名字，即入参的请求参数名字，如value=“item_id”表示请求的参数区中的名字为item_id的参数的值将传入；
	 * required：是否必须，默认是true，表示请求中一定要有相应的参数，否则将报； TTP Status 400 - Required Integer
	 * 		parameter 'XXXX' is not present   
	 * defaultValue：默认值，表示如果请求中没有同名参数时的默认值
	 */
	public String editItem(Model model, 
			@RequestParam(value="id") Integer item_id) throws Exception{
		System.out.println("进入了/editItem.action");
		//调用Service层根据id查询商品信息
		ItemsCustom items = itemsService.findItemsById(item_id);
		//相当于ModelAndView.addObject()
		model.addAttribute("items",items);
		
		return "items/editItems";
	}
	
	//商品信息提交页面
	@RequestMapping("/editItemSubmit")
	/**
	 * 页面中的input中的name和price等参数和ItemsCustom中的属性名称一致,所以可以将页面中的数据绑定到pojo中
	 * 在需要校验的参数前加@validated,在参数后面加入BindingResult bindingResult接受校验产生的信息
	 * 注意:存在多个需要校验的参数时,@validated和BindingResult bindingResult是配对出现的,且顺序固定
	 * 
	 * 数据回显:
	 * 1. springmvc默认对pojo数据进行回显
	 * 		pojo数据传入Controller,springmvc会将pojo数据传入request域中,key等于pojo名称(首字母小写)
	 * 2. @ModelAttribute可以将pojo数据回显到request指定的key中
	 */
	public String editItemSubmit(
			HttpServletRequest request, 
			Integer id, 
			@ModelAttribute(value="items") @Validated ItemsCustom itemsCustom,
			BindingResult bindingResult, 
			Model model, 
			MultipartFile items_pic //用于接受商品的图片
			) throws Exception {
		System.out.println("进入了/editItemSubmit.action");
		
		//判断是否存在错误信息
		if (bindingResult.hasErrors()) {
			//将错误信息存放到一个List集合中
			List<ObjectError> errorListTemp = bindingResult.getAllErrors();
			List<String> errorList = new ArrayList<String>();
			for(ObjectError objectError : errorListTemp) {
				//把返回错误的提示再次编码
				String strError=new String(objectError.getDefaultMessage().getBytes("ISO-8859-1"),"UTF-8");
				errorList.add(strError);
			}
			for(String str : errorList) 
				System.out.println(str);
			//将捕获到的错误信息显示到页面
			model.addAttribute("errorList", errorList);
			return "items/editItems";
		}
		
		// 获取原始文件名称
		String pictureFile_name = items_pic.getOriginalFilename();
		if(items_pic !=  null && pictureFile_name != null && pictureFile_name.length()>0) {
			// 新文件名称=随机数+原始文件的格式
			String newFileName = UUID.randomUUID().toString()
					+ pictureFile_name.substring(pictureFile_name.lastIndexOf("."));

			// 上传新的图片
			File newPic = new File("E:\\java_img\\itens_img\\" + newFileName);

			if (!newPic.exists()) {
				newPic.mkdirs();
			}
			// 将内存中的图片写入磁盘
			items_pic.transferTo(newPic);
			
			//如果上传成功要将图片位置写入到ItemsCustom中的pic字段中
			itemsCustom.setPic(newFileName);
		}
		
		
		
		itemsService.updateItemsById(id, itemsCustom);
		// 重定向
		// return "redirect:queryItems.action";
		// 页面转发
		// return "forward:queryItems.action";
		return "items/success";
	}
	
	//批量删除商品
	@RequestMapping("/deleteItems")
	public String deleteItems(Integer[] itemsIdArr) throws Exception {
		System.out.println("进入了/deleteItems.action");
		itemsService.deleteItemsBefore(itemsIdArr);
		itemsService.deleteItems(itemsIdArr);
		return "items/success";
	}
	
	//批量修改商品前的查询
	@RequestMapping("/queryItemsList")
	public ModelAndView queryItemsList(HttpServletRequest request, ItemsQueryVo itemsQueryVo) throws Exception{
		System.out.println("进入了/queryItemsList.action");
		//当ItemsQueryVo=null的时候,将查询全部商品列表
		List<ItemsCustom> itemsList = itemsService.findItemsList(itemsQueryVo);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("itemsList",itemsList);
		modelAndView.setViewName("items/queryItemsList");
		return modelAndView;
	}
	
	//批量修改商品的查询
	@RequestMapping("/editItemsList")
	public String editItemsList(ItemsQueryVo itemsQueryVo) throws Exception{
		System.out.println("进入了/editItemsList.action");
		return "items/success";
	}
}
