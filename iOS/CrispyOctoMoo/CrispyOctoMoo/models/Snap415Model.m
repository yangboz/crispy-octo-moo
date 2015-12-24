//
//  Snap415Model.m
//  CrispyOctoMoo
//
//  Created by yangboz on 12/12/15.
//  Copyright © 2015 SMARTKIT.INFO. All rights reserved.
//

#import "Snap415Model.h"

@implementation Snap415Model

@synthesize overviews,me,snap415Token,snap415UserTaxEvents,sqootDealsObject;
@synthesize incomeCategories,filingCategories,childrenCategories
,childrenKeywords,mortgageInterests,EVCredits;
@synthesize profile,eitcCreditObject;

- (id)init
{
    self = [super init];
    if (self) {
        //
        [Snap415Model sharedInstance].me = [[Snap415UserProfileBase alloc] init];
        [Snap415Model sharedInstance].profile = [[Snap415SimpleProfile alloc] init];
        [Snap415Model sharedInstance].eitcCreditObject = [[EITCCreditObject alloc] init];
    }
    return self;
}

#pragma interface of Snap415API

+(Snap415Model *)sharedInstance
{
    // 1
    static Snap415Model *_sharedInstance = nil;
    // 2
    static dispatch_once_t oncePredicate;
    // 3
    dispatch_once(&oncePredicate, ^{
        _sharedInstance = [[Snap415Model alloc] init];
    });
    return _sharedInstance;
}
@end
