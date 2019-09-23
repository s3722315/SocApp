import React from 'react'
import AllCourseComponent from '../../components/course-list/AllCoursesComponent.jsx'
import { mount, shallow, render } from 'enzyme'
import CourseDataService from '../../api/course-list/CourseDataService.js'


describe('AllCourseComponent', () => {
  it('should render correctly in "debug" mode', () => {
    const component = shallow(<AllCourseComponent debug />);
  
    expect(component).toMatchSnapshot();
  });
});


// describe('AllCourseComponent', () => {
//   it('should enroll', () => {
//     const component = mount(<AllCourseComponent debug />);
    
//     let course = [];
//     course = CourseDataService.retrieveACourse(1);

//     component
//     .find('#1')
//     .simulate('click');

//     expect(component).toMatchSnapshot();
//     component.unmount();
//   });
// });