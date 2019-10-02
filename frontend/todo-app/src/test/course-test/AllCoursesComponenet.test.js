import React from 'react'
import AllCourseComponent from '../../components/course-list/AllCoursesComponent.jsx'
import AllCourseComponentMock from './AllCourseComponentMock.jsx'
import { mount, shallow, render } from 'enzyme'


describe('AllCourseComponent', () => {
  it('should render correctly in "debug" mode', () => {
    const component = shallow(<AllCourseComponent debug />);
  
    expect(component).toMatchSnapshot();
  });
});

describe('AllCourseComponentMock', () => {
  it('enrolling from all courses list', () => {
    const component = mount(<AllCourseComponentMock debug />);
    
    component
    .find('#all-enroll-1')
    .simulate('click');

    expect(component).toMatchSnapshot();
    component.unmount();
  });

  it('unenrolling from all courses list', () => {
    const component = mount(<AllCourseComponentMock debug />);
    
    component
    .find('#all-unenroll-4')
    .simulate('click');

    expect(component).toMatchSnapshot();
    component.unmount();
  });

  it('unenrolling from my courses list', () => {
    const component = mount(<AllCourseComponentMock debug />);
    
    component
    .find('#my-unenroll-4')
    .simulate('click');

    expect(component).toMatchSnapshot();
    component.unmount();
  });
});