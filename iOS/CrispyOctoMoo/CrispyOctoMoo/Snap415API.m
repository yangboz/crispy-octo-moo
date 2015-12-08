//
//  Snap415API.m
//  CrispyOctoMoo
//
//  Created by yangboz on 12/7/15.
//  Copyright © 2015 SMARTKIT.INFO. All rights reserved.
//

#import "Snap415API.h"

#import <RestKit/RestKit.h>

//@see: http://stackoverflow.com/questions/5643514/how-to-define-an-nsstring-for-global-use
#ifdef DEV
#define kAPIEndpointHost @"http://ec2-54-218-63-45.us-west-2.compute.amazonaws.com:8083/api/v1/"
#else//LOCAL
#define kAPIEndpointHost @"http://localhost:8083/api/v1/"
#endif
//@see: https://github.com/yangboz/crispy-octo-moo/wiki/API-Services
#define kAPI_user_me (kAPIEndpointHost @"user/me")
#define kAPI_events (kAPIEndpointHost @"taxEvents")
#define kAPI_deals (kAPIEndpointHost @"deals")
#define kAPI_overviews (kAPIEndpointHost @"overviews")
//Notification Center post names;

#define kNCpN_get_overviews @"getOverviewsSucc"
#define kNCpN_get_me @"getMeSucc"
#define kNCpN_get_events @"getEventsSucc"
#define kNCpN_get_deals @"getDealsSucc"

@implementation Snap415API

- (id)init
{
    self = [super init];
    if (self) {
        //
    }
    return self;
}

#pragma interface of Snap415API

+(Snap415API *)sharedInstance
{
    // 1
    static Snap415API *_sharedInstance = nil;
    // 2
    static dispatch_once_t oncePredicate;
    // 3
    dispatch_once(&oncePredicate, ^{
            _sharedInstance = [[Snap415API alloc] init];
        });
    return _sharedInstance;
}

-(BOOL)syncUserProfile:(Snap415Token *)snap415Token
{
    //
    RKObjectMapping *responseMapping = [RKObjectMapping mappingForClass:[Snap415UserProfile class]];
    [responseMapping addAttributeMappingsFromArray:@[@"title", @"author", @"body"]];
    NSIndexSet *statusCodes = RKStatusCodeIndexSetForClass(RKStatusCodeClassSuccessful); // Anything in 2xx
    RKObjectMapping *requestMapping = [RKObjectMapping requestMapping]; // objectClass == NSMutableDictionary
    [requestMapping addAttributeMappingsFromArray:@[@"title", @"author", @"body"]];

    RKResponseDescriptor *articleDescriptor = [RKResponseDescriptor responseDescriptorWithMapping:responseMapping method:RKRequestMethodAny pathPattern:@"/articles" keyPath:@"article" statusCodes:statusCodes];
    
    // For any object of class Article, serialize into an NSMutableDictionary using the given mapping and nest
    // under the 'user/me' key path
    RKRequestDescriptor *requestDescriptor = [RKRequestDescriptor requestDescriptorWithMapping:requestMapping objectClass:[Snap415UserProfile class] rootKeyPath:@"data" method:RKRequestMethodAny];
    
    RKObjectManager *manager = [RKObjectManager managerWithBaseURL:[NSURL URLWithString:kAPI_user_me]];
    [manager addRequestDescriptor:requestDescriptor];
    [manager addResponseDescriptor:articleDescriptor];
    
    // POST to create
    [manager postObject:snap415Token path:kAPI_user_me parameters:nil success:nil failure:nil];
    //
    // PATCH to update
//    article.body = @"New Body";
//    [manager patchObject:article path:@"/articles/1234" parameters:nil success:nil failure:nil];
//    
//    // DELETE to destroy
//    [manager deleteObject:article path:@"/articles/1234" parameters:nil success:nil failure:nil];
    return YES;
}
-(NSArray *)getMe
{
    return NULL;
}
-(NSArray *)getEvents
{
    
    return NULL;
}
-(NSArray *)getDeals
{
    return NULL;
}
-(NSArray *)getOverviews
{
    [[RKObjectManager sharedManager] getObjectsAtPath:kAPI_overviews parameters:nil success:^(RKObjectRequestOperation *operation, RKMappingResult *mappingResult) {
            NSLog(@"RKMappingResult: %@", mappingResult.description);
            NSDictionary *dictObj = [NSDictionary dictionaryWithObject:mappingResult forKey:kNCpN_get_overviews];
            [[NSNotificationCenter defaultCenter] postNotificationName:kNCpN_get_overviews object:dictObj];
        }
        failure:^(RKObjectRequestOperation *operation, NSError *error) {
            NSLog(@"What do you mean by 'there is no coffee?': %@", error);
        }];
    return NULL;
}
@end
