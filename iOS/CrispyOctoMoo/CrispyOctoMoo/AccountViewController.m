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

Snap415UserProfileBase *snap415UserProfileBase;
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
    NSLog(@"snap415UserProfileBase:%@",snap415UserProfileBase.description);
    fbUserProfile = self.snap415UserProfileResult.fbUserProfile;
    NSLog(@"fbUserProfile:%@",fbUserProfile.description);
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
    self.lbl_simplyRelationshipStatus.text = (NSString *)[snap415UserProfileBase simplyRelationshipStatus];
    self.lbl_simplyEducation.text = (NSString *)[snap415UserProfileBase  simplyEducation];
    self.lbl_simplyBirthday.text = (NSString *)[snap415UserProfileBase simplyBirthday];
    self.lbl_simplyWork.text = (NSString *)[snap415UserProfileBase simplyWork];
 //
    self.lbl_city.text = (NSString *)[fbUserProfile objectForKey:@"city"];
    self.lbl_residents.text = [[NSString alloc] initWithFormat:@"birthday at %@ Years",(NSString *)[fbUserProfile objectForKey:@"birthday"]];
    NSObject *familyObj = [fbUserProfile objectForKey:@"family"];
    self.lbl_family.text = familyObj.description;
    //
    NSObject *locationObj = [fbUserProfile objectForKey:@"location"];
    self.lbl_location.text = locationObj.description;
    NSObject *workObj = [fbUserProfile objectForKey:@"work"];
    self.lbl_workStatus.text = workObj.description;;
    NSObject *educationObj = [fbUserProfile objectForKey:@"education"];
    self.lbl_education.text = educationObj.description;;
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

-(IBAction)addMore:(id)sender{
    STPopupController *popupController = [[STPopupController alloc] initWithRootViewController:[[UIStoryboard storyboardWithName:@"Main" bundle:nil] instantiateViewControllerWithIdentifier:@"DetailMePopupViewController"]];
    popupController.cornerRadius = 4;
    [popupController presentInViewController:self];
}

#pragma mark - Table view data source

//- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
//{
//    // Return the number of sections.
//    return 1;
//}
//
//- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
//{
//    // Return the number of rows in the section.
//    return [[fbUserProfile allKeys] count];
//}
//
//- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
//{
//    static NSString *CellIdentifier = @"Cell";
//    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:CellIdentifier];
//    if (cell == nil) {
//        cell = [[UITableViewCell alloc] initWithStyle:UITableViewCellStyleSubtitle reuseIdentifier:CellIdentifier];
//    }
//    // Configure the cell...
//    NSArray *allKeys = [fbUserProfile allKeys];
//    NSArray *allValues = [fbUserProfile allValues];
//    NSString *key = [allKeys objectAtIndex:indexPath.row];
//    cell.textLabel.text = key;
//    if (![[allValues objectAtIndex:indexPath.row] isKindOfClass:[NSNull class]]){
//        NSString *value = [allValues objectAtIndex:indexPath.row];
//        cell.detailTextLabel.text = value;
//        NSLog(@"index:%d,key-value:%@-%@",indexPath.row,key,value);
//    }
//    return cell;
//}

@end
