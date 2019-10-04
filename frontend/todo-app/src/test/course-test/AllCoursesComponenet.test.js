import React from 'react'
import AllCourseComponent from '../../components/course-list/AllCoursesComponent.jsx'
import AllCourseComponentMock from './AllCourseComponentMock.jsx'
import { mount, shallow, render } from 'enzyme'

// test if AllCourseComponent loads
describe('AllCourseComponent', () => {
  it('should render correctly in "debug" mode', () => {
    const component = shallow(<AllCourseComponent debug />);
  
    expect(component).toMatchSnapshot();
  });
});

// using AllCourseComponentMock for test
describe('AllCourseComponentMock', () => {
  // used to enroll into a course in the all courses list
  it('enrolling from all courses list', () => {
    const component = mount(<AllCourseComponentMock debug />);
    
    component
    .find('#all-enroll-1')
    .simulate('click');

    expect(component).toMatchSnapshot();
    component.unmount();
  });

  // used to enroll unenroll from the all course list
  it('unenrolling from all courses list', () => {
    const component = mount(<AllCourseComponentMock debug />);
    
    component
    .find('#all-unenroll-4')
    .simulate('click');

    expect(component).toMatchSnapshot();
    component.unmount();
  });

  // used to unenroll from the my course list
  it('unenrolling from my courses list', () => {
    const component = mount(<AllCourseComponentMock debug />);
    
    component
    .find('#my-unenroll-4')
    .simulate('click');

    expect(component).toMatchSnapshot();
    component.unmount();
  });
});