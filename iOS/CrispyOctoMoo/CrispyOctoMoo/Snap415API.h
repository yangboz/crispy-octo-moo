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
#import "Snap415Token.h"
#import "Snap415UserProfile.h"

@interface Snap415API : NSObject
+(Snap415API *)sharedInstance;

-(BOOL)syncUserProfile:(Snap415Token *)snap415Token;
-(NSArray *)getMe;
-(NSArray *)getEvents;
-(NSArray *)getDeals;
-(NSArray *)getOverview;
@end
