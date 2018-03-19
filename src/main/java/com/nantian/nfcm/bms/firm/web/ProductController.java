package com.nantian.nfcm.bms.firm.web;

import com.nantian.nfcm.bms.firm.service.ProductService;
import com.nantian.nfcm.bms.firm.vo.ProductBean;
import com.nantian.nfcm.util.ServiceException;
import com.nantian.nfcm.util.UploadFileUtils;
import com.nantian.nfcm.util.entity.Param;
import com.nantian.nfcm.util.service.ParamService;
import com.nantian.nfcm.util.vo.FileInfoBean;
import com.nantian.nfcm.util.vo.GridData;
import com.nantian.nfcm.util.vo.ResultInfo;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping("/product")
public class ProductController {
    private static Logger log = LoggerFactory.getLogger(ProductController.class);
    private ProductService productService;
    private ParamService paramService;
    private static String fileSavePath="fileSavePath";
    private static String productImageCountLimit="productImageCountLimit";
    private static String productVideoCountLimit="productVideoCountLimit";

    @Autowired
    public ProductController(ProductService productService,ParamService paramService) {
        this.productService = productService;
        this.paramService = paramService;
    }

	/**
	 * 根据产品编号查询产品信息
	 *
	 * @param productNum
	 * @return ResultInfo
	 * @throws Exception
	 */
	@RequestMapping("/findById")
	@ResponseBody
	private ResultInfo findById(String productNum) throws Exception {
		ResultInfo resultInfo = new ResultInfo();
		try {
			ProductBean productBean = productService.findById(productNum);
			resultInfo.setSuccess("true");
			resultInfo.setData(productBean);
		} catch (Exception e) {
			log.error(e.getMessage());
			resultInfo.setSuccess("false");
			resultInfo.setData(e.getMessage());
		}
		return resultInfo;
	}

	/**
	 * 根据条件分页查询产品信息
	 *
	 * @param page
	 * @param size
	 * @param productBean
	 * @return ResultInfo
	 * @throws Exception
	 */
	@RequestMapping("/findByCondition")
	@ResponseBody
	private ResultInfo findByCondition(int page, int size, ProductBean productBean) throws Exception {
		ResultInfo resultInfo = new ResultInfo();
		try {
			GridData<ProductBean> gridData = productService.findByCondition(page, size, productBean);
			resultInfo.setSuccess("true");
			resultInfo.setData(gridData.getData());
			resultInfo.setNumber(gridData.getNumber());
			resultInfo.setPage(gridData.getPage());
			resultInfo.setTotalPage(gridData.getTotalPage());
		} catch (Exception e) {
			log.error(e.getMessage());
			resultInfo.setSuccess("false");
			resultInfo.setData(e.getMessage());
		}
		return resultInfo;
	}

	/**
	 * 新增产品信息
	 *
	 * @param productBean
	 * @return ResultInfo
	 * @throws Exception
	 */
	@RequestMapping("/addProduct")
	@ResponseBody
	private ResultInfo addProduct(ProductBean productBean) throws Exception {
		ResultInfo resultInfo = new ResultInfo();
		try {
			ProductBean productBeanRet = productService.addProductInfo(productBean);
			resultInfo.setSuccess("true");
			resultInfo.setData(productBeanRet);
		} catch (Exception e) {
			log.error(e.getMessage());
			resultInfo.setSuccess("false");
			resultInfo.setData(e.getMessage());
		}
		return resultInfo;
	}

	/**
	 * 删除产品信息
	 *
	 * @param productBean
	 * @return ResultInfo
	 * @throws Exception
	 */
	@RequestMapping("/delProduct")
	@ResponseBody
	private ResultInfo delProduct(ProductBean productBean) throws Exception {
		ResultInfo resultInfo = new ResultInfo();
		try {
			Param fileSavePathParam = paramService.findParamById(fileSavePath);
			if (fileSavePathParam != null && fileSavePathParam.getParamValue() != null
					&& !"".equals(fileSavePathParam.getParamValue())) {
				// 文件保存路径配置了并且不为空
				String fileSavePath = fileSavePathParam.getParamValue();
				productService.delProductInfo(productBean, fileSavePath);
				resultInfo.setSuccess("true");
			} else {
				log.error("产品图片和视频保存目录参数未配置！[删除失败]");
				resultInfo.setSuccess("false");
				resultInfo.setData("产品图片和视频保存目录参数未配置！[删除失败]");
			}

		} catch (Exception e) {
			log.error(e.getMessage());
			resultInfo.setSuccess("false");
			resultInfo.setData(e.getMessage());
		}
		return resultInfo;
	}

	/**
	 * 更新产品信息
	 *
	 * @param productBean
	 * @return ResultInfo
	 * @throws Exception
	 */
	@RequestMapping("/updateProduct")
	@ResponseBody
	private ResultInfo updateProduct(ProductBean productBean) throws Exception {
		ResultInfo resultInfo = new ResultInfo();
		try {
			ProductBean productBeanRet = productService.updateProductInfo(productBean);
			resultInfo.setSuccess("true");
			resultInfo.setData(productBeanRet);
		} catch (Exception e) {
			log.error(e.getMessage());
			resultInfo.setSuccess("false");
			resultInfo.setData(e.getMessage());
		}
		return resultInfo;
	}
    
	/**
	 * 产品图片信息
	 *
	 * @param productBean
	 * @return ResultInfo
	 * @throws Exception
	 */
	@RequestMapping(value = "/imageUpload", method = RequestMethod.POST)
	@ResponseBody
	private ResultInfo imageUpload(String id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ResultInfo resultInfo = new ResultInfo();
		try {
			// 转型为MultipartHttpServletRequest
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("productImages");
			if (files != null && files.size() > 0) {
				Param imageCountLimitParam = paramService.findParamById(productImageCountLimit);
				if (imageCountLimitParam != null && imageCountLimitParam.getParamValue() != null
						&& !"".equals(imageCountLimitParam.getParamValue())) {
					// 图片限制数量配置了并且不为空
					String imageCountLimit = imageCountLimitParam.getParamValue();
					ProductBean productBeanTemp = productService.findById(id);
					String picUrl = productBeanTemp.getPictureUrl();
					int imageCount = new Integer(0);
					List<Integer> errorkeysList = new ArrayList<>();
					List<MultipartFile> fileList = new ArrayList<>();
					List<String> fileNameList = new ArrayList<>();
					List<String> picsNameList = new ArrayList<>();
					// 如果当前产品有图片文件，则需要判断文件名称是否已经存在
					if (picUrl != null && !"".equals(picUrl)) {
						String[] pics = picUrl.split("#");
						imageCount = pics.length;
						// 获取存在的图片
						for (int i = 0; i < imageCount; i++) {
							picsNameList.add(pics[i]);
						}
						// 判断上传文件是否在存在的文件中,并获取上传文件名称列表
						for (MultipartFile file : files) {
							String fileNametemp = file.getOriginalFilename();
							fileNameList.add(fileNametemp);
							if (!picsNameList.contains(fileNametemp)) {
								fileList.add(file);
							}
						}
						// 判断存在的图片是否在上传的文件中，如存在，则记录错误文件序号，用于报错显示
						for (int i = 0; i < imageCount; i++) {
							if (fileNameList.contains(pics[i])) {
								errorkeysList.add(i);
							}
						}
						// 如果上传的文件全部是存在的文件则报错
						if (fileList.size() == 0) {
							resultInfo.setError("上传的文件名称已经存在[上传失败]");
							resultInfo.setSuccess("false");
							resultInfo.setErrorkeys(errorkeysList);
							return resultInfo;
						}

					} else {
						fileList = files;
					}
					// 如果已有图片数量+上传图片数量>最大图片数量则报错
					if (imageCount + fileList.size() > Integer.parseInt(imageCountLimit)) {
						resultInfo.setSuccess("false");
						resultInfo.setError("上传后将超过最大限制数量{" + Integer.parseInt(imageCountLimit) + "}[上传失败]");
						// throw new ServiceException("已有图片数量+上传图片数量>最大图片数量[上传失败]");
					} else {
						// 文件数量符合要求
						Param fileSavePathParam = paramService.findParamById(fileSavePath);
						if (fileSavePathParam != null && fileSavePathParam.getParamValue() != null
								&& !"".equals(fileSavePathParam.getParamValue())) {
							// 图片保存路径配置啦并且不为空
							String savePath = fileSavePathParam.getParamValue() + "/" + id;
							// String savePath = path+"imageInfo";
							// String savePath = "D"+"://"+"pictureurl/";

							// 组合image名称，“#隔开”
							String pictureNames = ""; // 用来接收拼接各个图片的名字，并保存到数据库。
							// String[] paths= {"F:\\IMG_53361.jpg"};
							for (int i = 0; i < fileList.size(); i++) {
								if (!fileList.get(i).isEmpty()) {
									pictureNames = pictureNames
											+ UploadFileUtils.uploadFile(multipartRequest, fileList.get(i), savePath);

									// pictureurl=pictureurl+UploadFileUtils.uploadFile(savePath,paths);
								}
							}
							// 上传成功
							if (pictureNames != null && pictureNames.length() > 0) {
								System.out.println("上传成功！" + pictureNames); //
								ProductBean productBeanRet = productService.updatePicture(id, pictureNames);
								resultInfo.setSuccess("true");
								// 如果上传的文件中存在重复的文件
								if (errorkeysList.size() > 0) {
									resultInfo.setError("上传的文件中部分文件已经存在");
									resultInfo.setErrorkeys(errorkeysList);
								}
								resultInfo.setData(productBeanRet);
							} else {
								System.out.println("上传失败！");
								resultInfo.setSuccess("false");
								resultInfo.setError("上传失败！");
							}
						} else {
							log.error("产品图片和视频保存目录参数未配置！[上传失败]");
							resultInfo.setSuccess("false");
							resultInfo.setError("产品图片和视频保存目录参数未配置！[上传失败]");
						}
					}
				} else {
					log.error("产品图片限制数量参数未配置！[上传失败]");
					resultInfo.setSuccess("false");
					resultInfo.setError("产品图片限制数量参数未配置！[上传失败]");
				}
			} else {
				resultInfo.setSuccess("false");
				resultInfo.setError("上传文件为空或者不符合规定[上传失败]");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			resultInfo.setSuccess("false");
			resultInfo.setError(e.getMessage());
			resultInfo.setData(e.getMessage());
		}
		return resultInfo;
		/*
		 * try { ProductBean productBeanRet =
		 * productService.updateProductInfo(productBean); resultInfo.setSuccess("true");
		 * resultInfo.setData(productBeanRet); } catch (Exception e) {
		 * log.error(e.getMessage()); resultInfo.setSuccess("false");
		 * resultInfo.setData(e.getMessage()); } return resultInfo;
		 */
	}
    
    
	/**
	 * 根据产品编号查询产品图片信息
	 *
	 * @param productNum
	 * @return ResultInfo
	 * @throws Exception
	 */
	@RequestMapping("/findImageById")
	@ResponseBody
	private ResultInfo findImageById(String productNum) throws Exception {
		ResultInfo resultInfo = new ResultInfo();
		try {
			List<FileInfoBean> imageList = productService.findImageById(productNum);
			resultInfo.setSuccess("true");
			resultInfo.setData(imageList);
		} catch (Exception e) {
			log.error(e.getMessage());
			resultInfo.setSuccess("false");
			resultInfo.setData(e.getMessage());
		}
		return resultInfo;
	}
    
	/**
	 * 产品视频信息
	 *
	 * @param productBean
	 * @return ResultInfo
	 * @throws Exception
	 */
	@RequestMapping(value = "/movieUpload", method = RequestMethod.POST)
	@ResponseBody
	private ResultInfo movieUpload(String id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ResultInfo resultInfo = new ResultInfo();
		try {

			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("productMovies");
			if (files != null && files.size() > 0) {
				Param videoCountLimitParam = paramService.findParamById(productVideoCountLimit);
				if (videoCountLimitParam != null && videoCountLimitParam.getParamValue() != null
						&& !"".equals(videoCountLimitParam.getParamValue())) {
					// 视频限制数量配置了并且不为空
					String videoCountLimit = videoCountLimitParam.getParamValue();
					ProductBean productBeanTemp = productService.findById(id);
					String movUrl = productBeanTemp.getMovieUrl();
					int movieCount = new Integer(0);
					// TODO 视频文件重名控制
					List<Integer> errorkeysList = new ArrayList<>();
					List<MultipartFile> fileList = new ArrayList<>();
					List<String> fileNameList = new ArrayList<>();
					List<String> movsNameList = new ArrayList<>();
					// 如果当前产品有视频文件，则需要判断文件名称是否已经存在
					if (movUrl != null && !"".equals(movUrl)) {
						String[] movs = movUrl.split("#");
						movieCount = movs.length;

						// 获取存在的视频
						for (int i = 0; i < movieCount; i++) {
							movsNameList.add(movs[i]);
						}
						// 判断上传文件是否在存在的文件中,并获取上传文件名称列表
						for (MultipartFile file : files) {
							String fileNametemp = file.getOriginalFilename();
							fileNameList.add(fileNametemp);
							if (!movsNameList.contains(fileNametemp)) {
								fileList.add(file);
							}

						}
						// 判断存在的图片是否在上传的文件中，如存在，则记录错误文件序号，用于报错显示
						for (int i = 0; i < movieCount; i++) {
							if (fileNameList.contains(movs[i])) {
								errorkeysList.add(i);
							}
						}
						// 如果上传的文件全部是存在的文件则报错
						if (fileList.size() == 0) {
							resultInfo.setError("上传的文件名称已经存在[上传失败]");
							resultInfo.setSuccess("false");
							resultInfo.setErrorkeys(errorkeysList);
							return resultInfo;
						}

					} else {
						fileList = files;

					}
					// 如果已有视频数量+上传视频数量>最大视频数量则报错
					if (movieCount + files.size() > Integer.parseInt(videoCountLimit)) {
						resultInfo.setSuccess("false");
						resultInfo.setError("上传后将超过最大限制数量{" + Integer.parseInt(videoCountLimit) + "}[上传失败]");
						// throw new ServiceException("已有图片数量+上传图片数量>最大图片数量[上传失败]");
					} else {
						Param fileSavePathParam = paramService.findParamById(fileSavePath);
						if (fileSavePathParam != null && fileSavePathParam.getParamValue() != null
								&& !"".equals(fileSavePathParam.getParamValue())) {
							String savePath = fileSavePathParam.getParamValue() + "/" + id;

							// 组合名称，“#隔开”
							String movieNames = ""; // 用来接收拼接各个视频的名字，并保存到数据库。
							// String[] paths= {"F:\\IMG_53361.jpg"};
							for (int i = 0; i < fileList.size(); i++) {
								if (!fileList.get(i).isEmpty()) {
									movieNames = movieNames
											+ UploadFileUtils.uploadFile(multipartRequest, fileList.get(i), savePath);

									// pictureurl=pictureurl+UploadFileUtils.uploadFile(savePath,paths);
								}
							}
							// 上传成功
							if (movieNames != null && movieNames.length() > 0) {
								System.out.println("上传成功！" + movieNames); //
								ProductBean productBeanRet = productService.updateMovie(id, movieNames);
								resultInfo.setSuccess("true");
								// 如果上传的文件中存在重复的文件
								if (errorkeysList.size() > 0) {
									resultInfo.setError("上传的文件中部分文件已经存在");
									resultInfo.setErrorkeys(errorkeysList);
								}
								resultInfo.setData(productBeanRet);
							} else {
								System.out.println("上传失败！");
								resultInfo.setSuccess("false");
								resultInfo.setError("上传失败！");
							}
						} else {
							log.error("产品图片和视频保存目录参数未配置！[上传失败]");
							resultInfo.setSuccess("false");
							resultInfo.setError("产品图片和视频保存目录参数未配置！[上传失败]");
						}

					}
				} else {
					log.error("产品视频限制数量参数未配置！[上传失败]");
					resultInfo.setSuccess("false");
					resultInfo.setError("产品视频限制数量参数未配置！[上传失败]");
				}
			} else {
				resultInfo.setSuccess("false");
				resultInfo.setError("上传文件为空或者不符合规定[上传失败]");
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			resultInfo.setSuccess("false");
			resultInfo.setError(e.getMessage());
			resultInfo.setData(e.getMessage());
		}
		return resultInfo;
		/*
		 * try { ProductBean productBeanRet =
		 * productService.updateProductInfo(productBean); resultInfo.setSuccess("true");
		 * resultInfo.setData(productBeanRet); } catch (Exception e) {
		 * log.error(e.getMessage()); resultInfo.setSuccess("false");
		 * resultInfo.setData(e.getMessage()); } return resultInfo;
		 */
	}
    
    
	/**
	 * 根据产品编号查询产品视频信息
	 *
	 * @param productNum
	 * @return ResultInfo
	 * @throws Exception
	 */
	@RequestMapping("/findMovieById")
	@ResponseBody
	private ResultInfo findMovieById(String productNum) throws Exception {
		ResultInfo resultInfo = new ResultInfo();
		try {
			List<FileInfoBean> movieList = productService.findMovieById(productNum);
			resultInfo.setSuccess("true");
			resultInfo.setData(movieList);
		} catch (Exception e) {
			log.error(e.getMessage());
			resultInfo.setSuccess("false");
			resultInfo.setData(e.getMessage());
		}
		return resultInfo;
	}
    
	/**
	 * 删除产品图片信息
	 *
	 * @param productNum
	 * @param fileName
	 * @return ResultInfo
	 * @throws Exception
	 */
	@RequestMapping("/deleteImage")
	@ResponseBody
	private ResultInfo deleteImage(ProductBean productBean) throws Exception {
		ResultInfo resultInfo = new ResultInfo();
		try {
			String pictureName = productBean.getKey();
			String productNum = productBean.getProductNum();
			Param fileSavePathParam = paramService.findParamById(fileSavePath);
			if (fileSavePathParam != null && fileSavePathParam.getParamValue() != null
					&& !"".equals(fileSavePathParam.getParamValue())) {
				String savePath = fileSavePathParam.getParamValue() + "/" + productNum;
				FileInfoBean fileInfoBean = productService.deleteImage(pictureName, productNum, savePath);
				resultInfo.setSuccess("true");
				resultInfo.setData(fileInfoBean);
			} else {
				log.error("产品图片和视频保存目录参数未配置！[删除失败]");
				resultInfo.setSuccess("false");
				resultInfo.setError("产品图片和视频保存目录参数未配置！[删除失败]");
			}

		} catch (Exception e) {
			log.error(e.getMessage());
			resultInfo.setSuccess("false");
			resultInfo.setError(e.getMessage());
		}
		return resultInfo;
	}
    
	/**
	 * 删除产品视频信息
	 *
	 * @param productNum
	 * @param fileName
	 * @return ResultInfo
	 * @throws Exception
	 */
	@RequestMapping("/deleteMovie")
	@ResponseBody
	private ResultInfo deleteMovie(ProductBean productBean) throws Exception {
		ResultInfo resultInfo = new ResultInfo();
		try {
			String movieName = productBean.getKey();
			String productNum = productBean.getProductNum();
			Param fileSavePathParam = paramService.findParamById(fileSavePath);
			if (fileSavePathParam != null && fileSavePathParam.getParamValue() != null
					&& !"".equals(fileSavePathParam.getParamValue())) {
				String savePath = fileSavePathParam.getParamValue() + "/" + productNum;
				FileInfoBean fileInfoBean = productService.deleteMovie(movieName, productNum, savePath);
				resultInfo.setSuccess("true");
				resultInfo.setData(fileInfoBean);
			} else {
				log.error("产品图片和视频保存目录参数未配置！[删除失败]");
				resultInfo.setSuccess("false");
				resultInfo.setError("产品图片和视频保存目录参数未配置！[删除失败]");
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			resultInfo.setSuccess("false");
			resultInfo.setError(e.getMessage());
		}
		return resultInfo;
	}
}
