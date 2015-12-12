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
//#define DEV @"dev_aws"
#ifdef DEV
#define kAPIEndpointHost @"http://ec2-54-218-63-45.us-west-2.compute.amazonaws.com:8083/api/v1/"
#else//LOCAL
#define kAPIEndpointHost @"http://localhost:8083/api/v1/"
#endif
//@see: https://github.com/yangboz/crispy-octo-moo/wiki/API-Services
#define kAPI_user_me (@"user/me")
#define kAPI_events (@"taxEvents")
#define kAPI_deals (@"deals")
#define kAPI_overviews (@"overviews")


#import "WebSiteObject.h"

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
    [responseMapping addAttributeMappingsFromArray:@[@"snap415ID", @"fbUserProfile", @"liUserProfile",@"profileBase"]];
    NSIndexSet *statusCodes = RKStatusCodeIndexSetForClass(RKStatusCodeClassSuccessful); // Anything in 2xx
    RKObjectMapping *requestMapping = [RKObjectMapping requestMapping]; // objectClass == NSMutableDictionary
    [requestMapping addAttributeMappingsFromArray:@[@"id", @"token", @"provider"]];

    RKResponseDescriptor *respDescriptor = [RKResponseDescriptor responseDescriptorWithMapping:responseMapping method:RKRequestMethodAny pathPattern:kAPI_user_me keyPath:nil statusCodes:statusCodes];
    
    // For any object of class Article, serialize into an NSMutableDictionary using the given mapping and nest
    // under the 'user/me' key path
    RKRequestDescriptor *requestDescriptor = [RKRequestDescriptor requestDescriptorWithMapping:requestMapping objectClass:[Snap415Token class] rootKeyPath:nil method:RKRequestMethodAny];

    RKObjectManager *manager = [RKObjectManager managerWithBaseURL:[NSURL URLWithString:kAPIEndpointHost]];
    [manager addRequestDescriptor:requestDescriptor];
    [manager addResponseDescriptor:respDescriptor];
    // Set MIME Type to JSON
    manager.requestSerializationMIMEType = RKMIMETypeJSON;
    
    // POST to create
    [manager postObject:snap415Token path:kAPI_user_me parameters:nil success:^(RKObjectRequestOperation *operation, RKMappingResult *mappingResult) {
//        NSLog(@"SUCCESS: %@", mappingResult.array);
        RKLogInfo(@"Load item of Snap415UserProfile: %@", mappingResult.array);
        //
        //        NSLog(@"RKMappingResult: %@", mappingResult.description);
        NSDictionary *dictObj = [NSDictionary dictionaryWithObject:mappingResult.array forKey:kNCpN_load_me];
        [[NSNotificationCenter defaultCenter] postNotificationName:kNCpN_load_me object:dictObj];
    } failure:^(RKObjectRequestOperation *operation, NSError *error) {
        RKLogError(@"Operation failed with error: %@", error);
    }];
    //
    // PATCH to update
//    article.body = @"New Body";
//    [manager patchObject:article path:@"/articles/1234" parameters:nil success:nil failure:nil];
//    
//    // DELETE to destroy
//    [manager deleteObject:article path:@"/articles/1234" parameters:nil success:nil failure:nil];
    return YES;
}
-(void)loadMe
{
}
-(void)loadTaxEvents
{
}
-(void)loadDeals
{
}

-(void)loadOverviews
{
    RKObjectMapping* articleMapping = [RKObjectMapping mappingForClass:[WebSiteObject class]];
    [articleMapping addAttributeMappingsFromDictionary:@{
                                                         @"footer": @"footer",
                                                         @"body": @"body",
                                                         @"header": @"header"
                                                         }];
    
    RKResponseDescriptor *responseDescriptor = [RKResponseDescriptor responseDescriptorWithMapping:articleMapping method:RKRequestMethodAny pathPattern:nil keyPath:@"data" statusCodes:RKStatusCodeIndexSetForClass(RKStatusCodeClassSuccessful)];
    
    NSURL *URL = [NSURL URLWithString:[NSString stringWithFormat:@"%@%@",kAPIEndpointHost,kAPI_overviews]];
    NSURLRequest *request = [NSURLRequest requestWithURL:URL];
    RKObjectRequestOperation *objectRequestOperation = [[RKObjectRequestOperation alloc] initWithRequest:request responseDescriptors:@[ responseDescriptor ]];
    [objectRequestOperation setCompletionBlockWithSuccess:^(RKObjectRequestOperation *operation, RKMappingResult *mappingResult) {
        RKLogInfo(@"Load collection of WebSiteObjects: %@", mappingResult.array);
        //
//        NSLog(@"RKMappingResult: %@", mappingResult.description);
        NSDictionary *dictObj = [NSDictionary dictionaryWithObject:mappingResult.array forKey:kNCpN_load_overviews];
        [[NSNotificationCenter defaultCenter] postNotificationName:kNCpN_load_overviews object:dictObj];
        
    } failure:^(RKObjectRequestOperation *operation, NSError *error) {
        RKLogError(@"Operation failed with error: %@", error);
    }];
    //load begin
    [objectRequestOperation start];
}
@end
