//
//  AccountViewController.m
//  CrispyOctoMoo
//
//  Created by yangboz on 12/12/15.
//  Copyright © 2015 SMARTKIT.INFO. All rights reserved.
//

#import "AccountViewController.h"

@interface AccountViewController ()

@end

@implementation AccountViewController
@synthesize snap415UserProfileResult;

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
    self.lbl_first_name.text = @"aaaaa";
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

-(IBAction)addMore:(id)sender{
    
}
@end
