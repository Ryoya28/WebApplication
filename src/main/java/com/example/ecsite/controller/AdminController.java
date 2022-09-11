package com.example.ecsite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.ecsite.model.dao.GoodsRepository;
import com.example.ecsite.model.dao.UserRepository;
import com.example.ecsite.model.entity.Goods;
import com.example.ecsite.model.entity.User;
import com.example.ecsite.model.form.GoodsForm;
import com.example.ecsite.model.form.LoginForm;

//Controllerクラスはmodel,viewの橋渡しをする。
//@ControllerをつけることでSpringBootはそのクラスをControllerとして認識する
//@RequestMappingはリクエストURLに対して、どのクラス、メソッドの処理を行うかマッピングすることができる。
//localhost:8080/ecsite/adminでアクセスできるよう設定する
@Controller
@RequestMapping("/ecsite/admin")
public class AdminController {
	
	//UserRepository,GoodsRepositoryを読み込む。
	@Autowired
	private UserRepository userRepos;
	@Autowired
	private GoodsRepository goodsRepos;
	
	//adiminindex.htmlに遷移するメソッド
	@RequestMapping("/")
	public String index() {
		return "adminindex";
	}
	
	//"/welcome"をPOSTにする
	//welcome.htmlに遷移するメソッドを定義
	@PostMapping("/welcome")
	public String welcome(LoginForm form, Model m) {
		//下記ListはUserNameとpasswordでUserを検索する為のList
		List<User> users = userRepos.findByUserNameAndPassword(form.getUserName(), form.getPassword());
		
		//検索結果が管理者かどうか判断し、管理者だった場合のみ処理するもの。
		if(users != null && users.size() > 0) {
			boolean isAdmin = users.get(0).getIsAdmin() != "f";
			if(isAdmin) {
				List<Goods> goods = goodsRepos.findAll();
				m.addAttribute("userName", users.get(0).getUserName());
				m.addAttribute("password", users.get(0).getPassword());
				m.addAttribute("goods", goods);
			}
		}
		//管理者じゃなかったらwelcome.htmlを返す
		return "welcome";
	}
	
	//新規商品登録と削除機能を追加
	@RequestMapping("/goodsMst")
	public String goodsMst(LoginForm form, Model m) {
		m.addAttribute("userName", form.getUserName());
		m.addAttribute("password", form.getPassword());
		
		return "goodsmst";
	}
	
	@RequestMapping("/addGoods")
	public String addGoods(GoodsForm goodsForm, LoginForm loginForm, Model m) {
		m.addAttribute("userName", loginForm.getUserName());
		m.addAttribute("password", loginForm.getPassword());
		
		Goods newGoods = new Goods();
		newGoods.setGoodsName(goodsForm.getGoodsName());
		newGoods.setPrice(goodsForm.getPrice());
		goodsRepos.saveAndFlush(newGoods);
		
		return "forward:/ecsite/admin/welcome";
	}
	
	@ResponseBody
	@PostMapping("/api/deleteGoods")
	public String deleteApi(@RequestBody GoodsForm f, Model m) {
		try {
			goodsRepos.deleteById(f.getId());
		}catch(IllegalArgumentException e) {
			return "-1";
		}
		return "1";
	}
	
}
