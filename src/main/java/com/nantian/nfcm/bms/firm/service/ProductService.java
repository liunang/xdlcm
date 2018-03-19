package com.nantian.nfcm.bms.firm.service;

import java.io.File;
import java.util.List;

import com.nantian.nfcm.app.vo.AppTagBean;
import com.nantian.nfcm.bms.firm.vo.ProductBean;
import com.nantian.nfcm.util.ServiceException;
import com.nantian.nfcm.util.vo.FileInfoBean;
import com.nantian.nfcm.util.vo.GridData;

public interface ProductService {
    /**
     * 新增产品信息
     *
     * @param productBean
     * @return ProductBean
     * @throws ServiceException
     */
    public ProductBean addProductInfo(ProductBean productBean) throws ServiceException;

    /**
     * 删除产品信息
     *
     * @param productBean
     * @param fileSavePath
     * @throws ServiceException
     */
    public void delProductInfo(ProductBean productBean,String fileSavePath) throws ServiceException;

    /**
     * 修改产品信息
     *
     * @param productBean
     * @return ProductBean
     * @throws ServiceException
     */
    public ProductBean updateProductInfo(ProductBean productBean) throws ServiceException;

    /**
     * 根据ID查询产品信息
     *
     * @param productNum
     * @return ProductBean
     * @throws ServiceException
     */
    public ProductBean findById(String productNum) throws ServiceException;

    /**
     * 根据条件分页查询产品列表
     *
     * @param page
     * @param size
     * @param productBean firmNum|
     * @return Page<ProductBean>
     * @throws ServiceException
     */
    public GridData<ProductBean> findByCondition(int page, int size, ProductBean productBean) throws ServiceException;
    
    
    /**
     * 修改产品图片
     *
     * @param productBean
     * @return ProductBean
     * @throws ServiceException
     */
    public ProductBean updatePicture(String productNum,String pictureUrl) throws ServiceException;

    /**
     * 根据ID查询产品图片信息
     *
     * @param productNum
     * @return ProductBean
     * @throws ServiceException
     */
    public List<FileInfoBean> findImageById(String productNum) throws ServiceException;
    
    /**
     * 修改产品视频
     *
     * @param productBean
     * @return ProductBean
     * @throws ServiceException
     */
    public ProductBean updateMovie(String productNum,String pictureUrl) throws ServiceException;

    /**
     * 根据ID查询产品视频
     *
     * @param productNum
     * @return List<FileInfoBean>
     * @throws ServiceException
     */
    public List<FileInfoBean> findMovieById(String productNum) throws ServiceException;
    
    /**
     * 根据名称删除产品图片
     *
     * @param fileName
     * @return FileInfoBean
     * @throws ServiceException
     */
    public FileInfoBean deleteImage(String fileName,String productNum,String savePath) throws ServiceException;
    
    /**
     * 根据名称删除产品视频
     *
     * @param fileName
     * @return FileInfoBean
     * @throws ServiceException
     */
    public FileInfoBean deleteMovie(String fileName,String productNum,String savePath) throws ServiceException;
    
   
}
