/*
 * Author: Minyoung
 * CreateAt: 2021年10月14日23:44:45
 * License: MIT
 */
const singUpBtn = document.querySelector('#sign-up-btn')
const singInBtn = document.querySelector('#sign-in-btn')
const container = document.querySelector('.container')
singUpBtn.addEventListener('click', () => {
  container.classList.add('sign-up-mode')
})
singInBtn.addEventListener('click', () => {
  container.classList.remove('sign-up-mode')
})
getPIN.addEventListener('click', () => {
  var phonenum = document.getElementById('phoneNum').value
  $.ajax({
    type: "post",
    url: "/GetPIN",
    data: {
      "phonenum": phonenum
    },
    async: true, // 异步请求
    cache: false, // 设置为 false 将不缓存此页面
    dataType: 'json', // 返回对象
    success: function (data) {
      alert(data['msg']);
    },
    error: function (xhr, ajaxOptions, thrownError) {
      alert("系统异常！");
      // 请求失败函数
    }
  })

}

)