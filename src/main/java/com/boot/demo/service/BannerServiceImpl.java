package com.boot.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.boot.demo.repository.BannerRepository;

@Service
@Transactional
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerRepository bannerRepository;

}
