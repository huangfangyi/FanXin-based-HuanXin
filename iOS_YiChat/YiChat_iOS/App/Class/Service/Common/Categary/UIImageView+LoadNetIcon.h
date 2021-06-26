//
//  UIImageView+LoadNetIcon.h
//  YiChat_iOS
//
//  Created by 你是我的小呀小苹果 on 2019/5/28.
//  Copyright © 2019年 ZhangFengTechnology. All rights reserved.
//

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@interface UIImageView (LoadNetIcon)

- (void)sd_loadNetIconWithUrlStr:(NSString *)urlStr placeHolder:(UIImage *)placeHodler;

@end

NS_ASSUME_NONNULL_END
