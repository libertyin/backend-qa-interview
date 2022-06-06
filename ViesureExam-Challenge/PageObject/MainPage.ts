import { expect, Page } from "@playwright/test"

export class MainPage {
    readonly page: Page
    
    constructor(page: Page) {
        this.page = page
    }

    async fillSearch(searchText: string): Promise<void> {
        await this.page.fill('input[placeholder="Search city"]', searchText)
    }

    async clickSearchButton(): Promise<void> {
        await this.page.locator('button >> text=Search').click()
    }

    async clickItemFromSearch(searchItemName: string): Promise<void> {
        await this.page.locator(`.search-dropdown-menu span:text-is("${searchItemName}")`).click()
    }

    async expectTitleName(titleName: string): Promise<void> {
        await expect(this.page.locator('h1 .orange-text')).toHaveText(titleName)
    }

    async expectCityName(cityName: string): Promise<void> {
        await expect(this.page.locator(`h2:text-is("${cityName}")`)).toBeVisible()
    }

    async expectCityDate(date: string): Promise<void> {
        await expect(this.page.locator(`span:text-is("${date}")`)).toBeVisible()
    }

    async returnCityDate(utcHoursDifference: number): Promise<string> {
        var months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"]
        var datesInMonths = [31,28,31,30,31,30,31,31,30,31,30,31]
        var utcDay = new Date().getUTCDate()
        var utcMonth = new Date().getUTCMonth()
        var utcHoursModified = new Date().getUTCHours() + utcHoursDifference
        var utcMinutes = new Date().getUTCMinutes()

        /* getting name of month by number of month */
        var cityMonth = months[utcMonth.valueOf()]

        /* converting 24h format to 12am/pm format */
        var ampm = "am"
        var cityHours
        if(utcHoursModified > 12 && utcHoursModified < 24) {
            cityHours = utcHoursModified - 12;
            ampm = "pm";
        } else if(utcHoursModified >= 24) {
            cityHours = utcHoursModified - 24;
            utcDay += 1 
        } else cityHours = utcHoursModified

        /* adding 0 in front of hours with single digit */
        if(cityHours < 10) {
            cityHours = "0" + cityHours
        }
        
        /* moving to the next month in calendar if there is a new month in city */
        var cityDay
        var cityMonthIndex = months.indexOf(cityMonth)
        var datesMonthIndex = datesInMonths.indexOf(cityMonthIndex) 
        if(utcDay > datesInMonths[datesMonthIndex.valueOf()]) {
            cityMonthIndex = months.indexOf(cityMonth) + 1 
            cityDay = datesInMonths[datesMonthIndex.valueOf()]
        } else cityDay = utcDay

        /* adding 0 in front of minutes with single digit */
        var cityMinutes
        if(utcMinutes < 10) {
            cityMinutes = "0" + utcMinutes
        } else cityMinutes = utcMinutes

        const  cityDate = cityMonth + " " + cityDay + ", " + cityHours + ":" + cityMinutes + ampm
        return cityDate
    }

}
