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

NSDictionary * dealDict;

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
    dealDict = (NSDictionary *)[self.dealData objectForKey:@"deal"];
    NSLog(@"dealDict:%@",dealDict.description);
    //IBOutlet set values.
    self.title = (NSString *)[dealDict objectForKey:@"title"];
    self.lbl_category.text = (NSString *)[dealDict objectForKey:@"category_name"];
    self.lbl_provider.text = (NSString *)[dealDict objectForKey:@"provider_name"];
    self.lbl_created.text = (NSString *)[dealDict objectForKey:@"created_at"];
    if (![[dealDict objectForKey:@"expires_at"] isKindOfClass:[NSNull class]])
    {
        self.lbl_expires.text = (NSString *)[dealDict objectForKey:@"expires_at"];
    }
    //
    self.lbl_price.text = [(NSNumber *)[dealDict objectForKey:@"price"] stringValue];
    self.lbl_value.text = [(NSNumber *)[dealDict objectForKey:@"value"] stringValue];
    self.lbl_dAmount.text = [(NSNumber *)[dealDict objectForKey:@"discount_amount"] stringValue];
    self.lbl_dPercentage.text = [(NSNumber *)[dealDict objectForKey:@"discount_percentage"] stringValue];
    NSLog(@"number_sold:%@",[dealDict objectForKey:@"number_sold"]);
//    self.lbl_nSold.text = [(NSNumber *)[dealDict objectForKey:@"number_sold"] stringValue];
    self.lbl_nSold.text = (NSString *)[dealDict objectForKey:@"number_sold"];
    //
//    self.lbl_description.text = (NSString *)[dealDict objectForKey:@"description"];
    if (![[dealDict objectForKey:@"description"] isKindOfClass:[NSNull class]]){
    NSAttributedString *attributedString_description = [[NSAttributedString alloc] initWithData:[(NSString *)[dealDict objectForKey:@"description"] dataUsingEncoding:NSUnicodeStringEncoding] options:@{ NSDocumentTypeDocumentAttribute: NSHTMLTextDocumentType } documentAttributes:nil error:nil];
    self.lbl_description.attributedText = attributedString_description;
    }
    self.lbl_image_url.text = (NSString *)[dealDict objectForKey:@"image_url"];
    self.lbl_link_url.text = (NSString *)[dealDict objectForKey:@"url"];
    self.lbl_fine_print.text = (NSString *)[dealDict objectForKey:@"fine_print"];
    NSDictionary *sqootMerchant = (NSDictionary *)[dealDict objectForKey:@"merchant"];
    self.lbl_merchant.text = [sqootMerchant objectForKey:@"name"];
    self.lbl_share.text = (NSString *)[dealDict objectForKey:@"untracked_url"];
    
}
//@see: http://stackoverflow.com/questions/12961345/uinavigationcontroller-not-showing-back-button
-(IBAction) dismiss:(id) sender
{
    [self dismissViewControllerAnimated:YES completion:nil];
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
