package com.nantian.nfcm.bms.loan.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nantian.nfcm.bms.loan.dao.LoanApplicationDao;
import com.nantian.nfcm.bms.loan.entity.LoanApplication;
import com.nantian.nfcm.bms.loan.entity.LoanInfo;
import com.nantian.nfcm.bms.loan.vo.LoanBean;
import com.nantian.nfcm.util.DateUtil;
import com.nantian.nfcm.util.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanApplicationService {
    private LoanApplicationDao loanApplicationDao;

    @Autowired
    public LoanApplicationService(LoanApplicationDao loanApplicationDao) {
        this.loanApplicationDao = loanApplicationDao;
    }

    public LoanApplication findById(Long loanId ) throws ServiceException{
        return loanApplicationDao.findOne(loanId);
    }

    public LoanBean addLoanApplication(LoanBean loanBean) throws ServiceException{
        String loanJournal = DateUtil.getCurrentTime("yyyyMMddHHmmss")+loanBean.getOperator();
        LoanInfo loanInfo = loanBean.getLoanInfo();

        ObjectMapper mapper = new ObjectMapper();
        try {
            String detail = mapper.writeValueAsString(loanInfo);
            loanBean.setDetail(detail);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return loanApplicationDao.save(loanBean);
    }

}
