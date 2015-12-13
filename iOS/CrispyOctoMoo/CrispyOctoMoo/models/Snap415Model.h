//
//  Snap415Model.h
//  CrispyOctoMoo
//
//  Created by yangboz on 12/12/15.
//  Copyright © 2015 SMARTKIT.INFO. All rights reserved.
//

#import <Foundation/Foundation.h>

#import "WebSiteObject.h"
#import "Snap415UserProfile.h"
#import "Snap415Token.h"
#import "Snap415UserTaxEvents.h"
#import "SqootDealsObject.h"

@interface Snap415Model : NSObject
+(Snap415Model *)sharedInstance;

@property (strong, nonatomic) Snap415Token *snap415Token;
@property (strong, nonatomic) NSArray *overviews;
@property (strong, nonatomic) Snap415UserProfile * snap415UserProfile;
@property (strong, nonatomic) Snap415UserTaxEvents *snap415UserTaxEvents;
@property (strong, nonatomic) SqootDealsObject *sqootDealsObject;

@end
