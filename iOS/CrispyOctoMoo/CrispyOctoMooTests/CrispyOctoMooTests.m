//
//  CrispyOctoMooTests.m
//  CrispyOctoMooTests
//
//  Created by yangboz on 12/7/15.
//  Copyright © 2015 SMARTKIT.INFO. All rights reserved.
//

#import <XCTest/XCTest.h>
#import "Snap415API.h"

@interface CrispyOctoMooTests : XCTestCase

@end

@implementation CrispyOctoMooTests

Snap415API *_snap415API;



- (void)setUp {
    [super setUp];
    // Put setup code here. This method is called before the invocation of each test method in the class.
    _snap415API = [Snap415API sharedInstance];
}

- (void)tearDown {
    // Put teardown code here. This method is called after the invocation of each test method in the class.
    [super tearDown];
}

- (void)testExample {
    // This is an example of a functional test case.
    // Use XCTAssert and related functions to verify your tests produce the correct results.
    NSArray *overviews = [_snap415API getOverviews];
    XCTAssertNotNil(overviews);
}

- (void)testPerformanceExample {
    // This is an example of a performance test case.
    [self measureBlock:^{
        // Put the code you want to measure the time of here.
    }];
}

@end
