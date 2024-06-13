package com.salmon.test.step_definitions;

import com.salmon.test.page_objects.*;
import cucumber.api.java.en.And;

public class BlogPageSteps {

    public BlogPage blogPage;

    public BlogPageSteps(BlogPage blogPage) {
        this.blogPage = blogPage;
    }

    @And("^navigate to each and every page in blog page$")
    public void navigatetoEachandEveryPageInBlogPage() throws InterruptedException {
        blogPage.navigatetoEachandEveryPageInBlogPage();
    }
}
