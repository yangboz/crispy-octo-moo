//
//  DetailDealViewController.m
//  CrispyOctoMoo
//
//  Created by yangboz on 12/20/15.
//  Copyright © 2015 SMARTKIT.INFO. All rights reserved.
//

#import "DetailDealViewController.h"

@interface DetailDealViewController ()

@end

@implementation DetailDealViewController
@synthesize dealData;

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}
- (void)viewDidAppear:(BOOL)animated
{
    NSLog(@"self.dealData:%@",self.dealData.description);
    NSLog(@"self.dealData.deal:%@",[(NSDictionary *)[self.dealData objectForKey:@"deal"] description]);
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
