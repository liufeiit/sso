package me.sso.ti.srv.impl;

import java.util.ArrayList;
import java.util.List;

import me.sso.ti.dataobject.StyleDO;
import me.sso.ti.result.Result;
import me.sso.ti.result.ResultCode;
import me.sso.ti.srv.BaseService;
import me.sso.ti.srv.StyleService;
import me.sso.ti.vo.StyleVO;

import org.springframework.stereotype.Service;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0.0
 * @since 2015年2月9日 下午10:06:23
 */
@Service(value = "styleService")
public class DefaultStyleService extends BaseService implements StyleService {

	@Override
	public Result getStyleList() {
		List<StyleDO> styles = styleDAO.findAll();
		List<StyleVO> styleList = new ArrayList<StyleVO>();
		for (StyleDO style : styles) {
			StyleVO vo = StyleVO.newInstance(style);
			if(vo == null) {
				continue;
			}
			styleList.add(vo);
		}
		return Result.newSuccess().with(ResultCode.Success).with("styleList", styleList);
	}

	@Override
	public Result getStyle(Long id) {
		StyleDO style = styleDAO.get(id);
		StyleVO vo = StyleVO.newInstance(style);
		return Result.newSuccess().with(ResultCode.Success).with("style", vo);
	}
}