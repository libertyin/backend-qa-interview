import { test, Page } from "@playwright/test"

import { MainPage } from "../PageObject/MainPage"
import { mainPageUrl } from "../test-data/projectUrls"


test.describe('Weather page TCs', () => {
    let page: Page

    test.beforeEach(async({ browser }) => {
        page = await browser.newPage()
        await page.goto(mainPageUrl)
    })

    test('Check page title', async () => {
        const mainPage = new MainPage(page)

        await mainPage.expectTitleName("OpenWeather")
    })

    test('Check city search field', async () => {
        const mainPage = new MainPage(page)

        await test.step("step 1", async() => {
           await mainPage.fillSearch("Sydney")
           await mainPage.clickSearchButton()
           await mainPage.clickItemFromSearch("Sydney, AU")
        })

        await test.step("step 2", async() => {
            await mainPage.expectCityName("Sydney, AU")
        })

        await test.step("step 3", async() => {
            const aestUTCHoursDifference = 10
            const aestZoneDate = await mainPage.returnCityDate(aestUTCHoursDifference)
            await mainPage.expectCityDate(aestZoneDate)
        })

    })

    test.afterEach(async() => {
        await page.close()
    })
})
