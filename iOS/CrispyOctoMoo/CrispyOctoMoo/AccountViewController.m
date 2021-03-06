//
//  AccountViewController.m
//  CrispyOctoMoo
//
//  Created by yangboz on 12/12/15.
//  Copyright © 2015 SMARTKIT.INFO. All rights reserved.
//

#import "AccountViewController.h"

#import <Foundation/Foundation.h>

@interface AccountViewController ()

@end

@implementation AccountViewController
@synthesize snap415UserProfileResult;

//Snap415UserProfileBase *snap415UserProfileBase;
NSDictionary *snap415UserProfileBase;
NSDictionary *fbUserProfile;

- (void)viewDidLoad {
    [super viewDidLoad];
    //Variables init


    // Uncomment the following line to preserve selection between presentations.
    // self.clearsSelectionOnViewWillAppear = NO;
    
    // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
    // self.navigationItem.rightBarButtonItem = self.editButtonItem;
    //NotificationCenter
//    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(loadMeHandler:) name:kNCpN_load_me object:nil] ;
    self.snap415UserProfileResult = [[Snap415Model sharedInstance] me];
    NSLog(@"self.snap415UserProfileResult:%@",self.snap415UserProfileResult.description);
    snap415UserProfileBase = self.snap415UserProfileResult.profileBase;
//    NSLog(@"snap415UserProfileBase:%@",snap415UserProfileBase.description);
    fbUserProfile = self.snap415UserProfileResult.fbUserProfile;
//    NSLog(@"fbUserProfile:%@",fbUserProfile.description);
//    self.view = [[UIView alloc]initWithFrame:CGRectMake(0,0,320,480)];
}

//-(void)loadMeHandler:(NSNotification *) notification{
//    //    NSLog(@"loadMeHandler:%@",notification.userInfo);
//    self.snap415UserProfileResult = [(NSDictionary *)notification.object objectForKey:kNCpN_load_me];
//    NSLog(@"snap415UserProfileResult:%@",self.snap415UserProfileResult.description);
//    //
//}

- (void)viewDidAppear:(BOOL)animated {
    [super viewDidAppear:animated];
    //
//    for (id key in fbUserProfile) {
//        NSLog(@"key: %@, value: %@ \n", key, [fbUserProfile objectForKey:key]);
//    }
 //
    self.lbl_firstName.text = (NSString *)[fbUserProfile objectForKey:@"firstName"];
    self.lbl_gender.text = (NSString *)[fbUserProfile objectForKey:@"gender"];
    self.lbl_simplyRelationshipStatus.text = [snap415UserProfileBase objectForKey:@"simplyRelationshipStatus"];
    self.lbl_simplyEducation.text = [snap415UserProfileBase objectForKey:@"simplyEducation"];
    self.lbl_simplyBirthday.text = [snap415UserProfileBase objectForKey:@"simplyBirthday"];
    self.lbl_simplyWork.text = [snap415UserProfileBase objectForKey:@"simplyWork"];
 //
    self.lbl_city.text = (NSString *)[fbUserProfile objectForKey:@"city"];
    self.lbl_residents.text = [[NSString alloc] initWithFormat:@"birthday at %@ Years",(NSString *)[fbUserProfile objectForKey:@"birthday"]];
    NSObject *familyObj = [fbUserProfile objectForKey:@"family"];
    self.lbl_family.text = familyObj.description;
    //
    NSDictionary *locationObj = [fbUserProfile objectForKey:@"location"];
    if (![locationObj isKindOfClass:[NSNull class]]){
        self.lbl_location.text = [locationObj objectForKey:@"name"];
    }
    NSDictionary *workObj = [(NSArray *)[fbUserProfile objectForKey:@"work"] objectAtIndex:0];
    if (![workObj isKindOfClass:[NSNull class]]){
        self.lbl_workStatus.text = [(NSDictionary *)[workObj objectForKey:@"employer"] objectForKey:@"name"];
    }
    NSDictionary *educationObj = [(NSArray *)[fbUserProfile objectForKey:@"education"] objectAtIndex:0];
    if (![educationObj isKindOfClass:[NSNull class]]){
        NSDictionary *schoolObj = (NSDictionary *)[educationObj objectForKey:@"school"];
        NSString *educationStr = [[NSString alloc]  initWithFormat:@"%@(%@)",[schoolObj objectForKey:@"name"],[educationObj objectForKey:@"type"]];
        self.lbl_education.text = educationStr;
    }
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}



// #pragma mark - Navigation
// // In a storyboard-based application, you will often want to do a little preparation before navigation
// - (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
// // Get the new view controller using [segue destinationViewController].
// // Pass the selected object to the new view controller.
// }


@end
