//
//  Snap415API.h
//  CrispyOctoMoo
//
//  Created by yangboz on 12/7/15.
//  Copyright © 2015 SMARTKIT.INFO. All rights reserved.
//

#import <Foundation/Foundation.h>
//@see Facade design pattern at http://www.raywenderlich.com/46988/ios-design-patterns
//@see Snap415API: https://github.com/yangboz/crispy-octo-moo/wiki/API-Services
//To be implemented of services
//TODO:EITCCreditService
//TODO:IncomeCategoryService
//TODO:FilingCategoryService
//TODO:ChildrenCategoryService
//TODO:EVCreditService
//TODO:MortgageInterestService
//TODO:ChildrenKeywordsService
//TODO:UserProfileService
//TODO:UserMeService
//TODO:FbUserProfileService
//TODO:LiUserProfileService
//TODO:OverviewService
//TODO:DealService
//TODO:CategoryDealService
//TODO:TaxEventService
//TODO:UserTaxEventService

#import "Snap415Token.h"
#import "Snap415UserProfile.h"
#import "Snap415Model.h"
#import "Snap415UserTaxEvents.h"

//Notification Center post names;
#define kNCpN_load_overviews @"loadOverviewsSucc"
#define kNCpN_load_me @"loadMeSucc"
#define kNCpN_load_tax_events @"loadTaxEventsSucc"
#define kNCpN_load_deals @"loadDealsSucc"

@interface Snap415API : NSObject
+(Snap415API *)sharedInstance;

-(void)syncUserProfile;
-(void)loadTaxEvents;
-(void)loadDeals;
-(void)loadOverviews;
@end
